package com.example.learnjapanwords;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.io.RandomAccessRead;
import com.tom_roush.pdfbox.pdfparser.PDFParser;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.text.PDFTextStripper;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFile {

    private String extractPDFText(AssetManager assetManager) {
        String parsedText = null;
        PDDocument document = null;
        try{
            document = PDDocument.load(assetManager.open("jap.pdf"));
        }catch (IOException e) {
            Log.e("Error", String.valueOf(e));
        }

        try{
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            pdfTextStripper.setStartPage(0);
            pdfTextStripper.setEndPage(1);
            parsedText = pdfTextStripper.getText(document);
        } catch (IOException e1) {
            Log.e("Error", String.valueOf(e1));
        } finally {
            try {
                if (document != null) document.close();
            }
            catch (IOException e2) {
                Log.e("Error", String.valueOf(e2));
            }
        }

        return parsedText;

    }

    private void divideRussianJapan(String text, DBHelper dbHelper) {
        String[] parsedText = text.split(" ");
        dbHelper.dbEnterData(parsedText[2], parsedText[1]);
        dbHelper.close();
        return;
    }

    private void divide(String text, DBHelper dbHelper) {
        String[] parsedText = text.split(";");
//        for (String word : parsedText) {
//            divideRussianJapan(word, dbHelper);
//        }
        for (int i = 0; i < parsedText.length; i++) {
            divideRussianJapan(parsedText[i], dbHelper);
        }

    }

    public String  parsePDFile(AssetManager assetManager, DBHelper dbHelper) {
        String text = extractPDFText(assetManager);
        divide(text, dbHelper);
        divideRussianJapan(text, dbHelper);

        return text;
    }
}
