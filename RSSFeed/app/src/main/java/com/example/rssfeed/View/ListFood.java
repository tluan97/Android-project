package com.example.rssfeed.View;

import com.example.rssfeed.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListFood extends AppCompatActivity {

    ListView lvRss;
    ArrayList<String> titles;
    ArrayList<String> links;
    ImageView logo;
    public static String RSSLINK="WebActivity.url";
    public static String RSSTITLE="Title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        logo = (ImageView)findViewById(R.id.imageView);

        logo.setImageResource(R.drawable.fdc_icon);
        String url = getIntent().getStringExtra(RSSLINK);
        String title = getIntent().getStringExtra(RSSTITLE);
        //getSupportActionBar().setIcon(R.drawable.share_logo);
        getSupportActionBar().setTitle("Specialtyfood.com - "+title);

        lvRss = (ListView) findViewById(R.id.lvRss);

        titles = new ArrayList<String>();
        links = new ArrayList<String>();

        lvRss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(ListFood.this);
                alertdialog.setTitle(titles.get(position));
                alertdialog.setIcon(R.drawable.share_logo);

                alertdialog.setPositiveButton("More Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse(links.get(position));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                alertdialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertdialog.show();
            }
        });

        new ProcessInBackground().execute(url);
    }

    public InputStream getInputStream(URL url)
    {
        try
        {
            //openConnection() returns instance that represents a connection to the remote object referred to by the URL
            //getInputStream() returns a stream that reads from the open connection
            return url.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<String, Void, Exception>
    {
        Exception exception = null;
        @Override
        protected Exception doInBackground(String... params) {
            try
            {
                URL url = new URL(params[0]);
                //creates new instance of PullParserFactory that can be used to create XML pull parsers
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                //Specifies whether the parser produced by this factory will provide support
                //for XML namespaces
                factory.setNamespaceAware(false);
                //creates a new instance of a XML pull parser using the currently configured
                //factory features
                XmlPullParser xpp = factory.newPullParser();
                // We will get the XML from an input stream
                xpp.setInput(getInputStream(url), "UTF_8");
                /* We will parse the XML content looking for the "<title>" tag which appears inside the "<item>" tag.
                 * We should take into consideration that the rss feed name is also enclosed in a "<title>" tag.
                 * Every feed begins with these lines: "<channel><title>Feed_Name</title> etc."
                 * We should skip the "<title>" tag which is a child of "<channel>" tag,
                 * and take into consideration only the "<title>" tag which is a child of the "<item>" tag
                 *
                 * In order to achieve this, we will make use of a boolean variable called "insideItem".
                 */
                boolean insideItem = false;
                // Returns the type of current event: START_TAG, END_TAG, START_DOCUMENT, END_DOCUMENT etc..
                int eventType = xpp.getEventType(); //loop control variable

                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    //if we are at a START_TAG (opening tag)
                    if (eventType == XmlPullParser.START_TAG)
                    {
                        //if the tag is called "item"
                        if (xpp.getName().equalsIgnoreCase("item"))
                        {
                            insideItem = true;
                        }
                        //if the tag is called "title"
                        else if (xpp.getName().equalsIgnoreCase("title"))
                        {
                            if (insideItem)
                            {
                                // extract the text between <title> and </title>
                                titles.add(xpp.nextText());
                            }
                        }
                        //if the tag is called "link"
                        else if (xpp.getName().equalsIgnoreCase("link"))
                        {
                            if (insideItem)
                            {
                                // extract the text between <link> and </link>
                                links.add(xpp.nextText());
                            }
                        }
                    }
                    //if we are at an END_TAG and the END_TAG is called "item"
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item"))
                    {
                        insideItem = false;
                    }
                    eventType = xpp.next(); //move to next element
                }
            }
            catch (MalformedURLException e)
            {
                exception = e;
            }
            catch (XmlPullParserException e)
            {
                exception = e;
            }
            catch (IOException e)
            {
                exception = e;
            }
            return exception;
        }

        @Override
        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListFood.this, android.R.layout.simple_list_item_1, titles);
            lvRss.setAdapter(adapter);
        }
    }
}