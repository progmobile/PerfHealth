package com.example.quentindoucet.perfhealth.vue;

import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controler.ActionManager;
import com.example.quentindoucet.perfhealth.model.Action;
import com.example.quentindoucet.perfhealth.model.Historique;
import com.example.quentindoucet.perfhealth.model.Personne;
import com.firebase.client.Firebase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoriqueActivity extends AppCompatActivity {


    private static final String TAG = "HISTORIQUE_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
/*
        Personne p = new Personne(1,"DOUCET","Quentin",23,"M",187,80);
        Action a = new Action(1,"Se laver les mains", "un lavage de 30s",new Date());
        Action aa = new Action(1,"Se brosser les dents", "Un brossage de 3min",new Date());

        ArrayList<Action> listA = new ArrayList();
        listA.add(a);
        listA.add(aa);
*/

        ActionManager actionManager = new ActionManager();

        Personne p = new Personne(1,"DOUCET","Quentin",23,"M",187,80);

        ArrayList<Action> listA = actionManager.getListAction();

        Log.e("Get Data", listA.size()+"");
        Historique h = new Historique(1,listA,p);


        ListAdapter myAdapter = new com.firebase.ui.FirebaseListAdapter<Action>(this,String.class,android.R.layout.simple_list_item_1,actionManager.getAction()) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setText(s);
            }
        };
        final ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(myAdapter);

        /*

        // Définition des colonnes
        // NB : SimpleCursorAdapter a besoin obligatoirement d'un ID nommé "_id"
        String[] columns = new String[] { "_id", "col1", "col2", "col3" };

        // Définition des données du tableau
        // les lignes ci-dessous ont pour seul but de simuler un objet de type Cursor pour le passer au SimpleCursorAdapter.

        MatrixCursor matrixCursor= new MatrixCursor(columns);
       // startManagingCursor(matrixCursor);
        for (Action action : listA) {

            SimpleDateFormat simpleDateFormatHr = new SimpleDateFormat("h:mm a");
            String hrStr = simpleDateFormatHr.format(action.getDateAction());
            SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = simpleDateFormatDate.format(action.getDateAction());

            matrixCursor.addRow(new Object[] { action.getId(),action.getNomAction(), hrStr, dateStr});
        }

        // on prendra les données des colonnes 1 et 2...
        String[] from = new String[] {"col1", "col2" ,"col3"};

        // ...pour les placer dans les TextView définis dans "row_item.xml"
        int[] to = new int[] { R.id.textViewCol1, R.id.textViewCol2, R.id.textViewDate};

        // création de l'objet SimpleCursorAdapter...
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_item, matrixCursor, from, to, 0);

        // ...qui va remplir l'objet ListView
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
*/
    }

}
