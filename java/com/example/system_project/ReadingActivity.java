package com.example.system_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ReadingActivity extends Activity implements OnPageChangeListener,OnLoadCompleteListener {


    private static final String TAG = ReadingActivity.class.getSimpleName();

   // public static final String PDF_Link = "https://gelleresol.weebly.com/uploads/3/0/1/6/30164729/the_help_-_kathryn_stockett.pdf";
   String PDF_Link;
    public static final String MY_Pdf = "my_book.pdf";
    private AppCompatSeekBar seekbar;
    private PDFView pdfView;
    private TextView txtView;

    Integer pageNumber;
    String pdfFileName;

    Integer currentPageNumber;
    Integer savedPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Intent intent = getIntent();
        PDF_Link = intent.getStringExtra("book");
        pdfView=findViewById(R.id.pdfView);
        txtView=findViewById(R.id.txtview);
        initSeekbar();
        downloadPdf(MY_Pdf);

        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);

        savedPage = mySharedPreferences.getInt("retrievedPage",0);

        pageNumber = savedPage;
        //pdfView = (PDFView)findViewById(R.id.pdfView);
        //displayFromAsset(PDF_FILE);


    }
    private void initSeekbar(){
        seekbar=findViewById(R.id.seekbar);
        seekbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        seekbar.getThumb().setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int val=(progress*(seekBar.getWidth()-3*seekBar.getThumbOffset()))/seekBar.getMax();
                txtView.setText(""+progress);
                txtView.setX(seekBar.getX()+val+seekBar.getThumbOffset()/2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void downloadPdf(final String filename){
        new AsyncTask<Void,Integer,Boolean>(){
            @Override
            protected Boolean doInBackground(Void... params) {
                return downloadPdf();
            }
            @Nullable
            private Boolean downloadPdf(){
                try{
                    File file=getFileStreamPath(filename);
                    if (file.exists())
                        return true;
                    try{
                        FileOutputStream fileOutputStream=openFileOutput(filename,Context.MODE_PRIVATE);
                        URL u=new URL(PDF_Link);
                        URLConnection connn=u.openConnection();
                        int contentLenght=connn.getContentLength();
                        InputStream input=new BufferedInputStream(u.openStream());
                        byte data[]=new byte[contentLenght];
                        long total=0;
                        int count;
                        while ((count=input.read(data)) != -1){
                            total+=count;
                            publishProgress((int) ((total*100)/contentLenght));
                            fileOutputStream.write(data,0,count);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        input.close();
                        return true;
                    }catch (final  Exception e){
                        e.printStackTrace();
                        return false;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                seekbar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (aBoolean){
                    openPdf(filename);
                }
                else
                {
                    Toast.makeText(ReadingActivity.this,"failed",Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();

    }
    private void openPdf(String filename){
        try{
            File file=getFileStreamPath(filename);
            Log.e("file: ","file:"+file.getAbsolutePath());
            seekbar.setVisibility(View.GONE);
            pdfView.setVisibility(View.VISIBLE);
            pdfView.fromFile(file)
                    .defaultPage(pageNumber)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .onPageChange(this)
                    .enableAnnotationRendering(true)
                    .onLoad(this)
                    .scrollHandle(new DefaultScrollHandle(this))
                    .load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onPageChanged(int page, int pageCount) {

        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));

    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }
    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences mySharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();


        savedPage = pdfView.getCurrentPage();
        myEditor.putInt("retrievedPage",savedPage);
        myEditor.apply();


    }

}