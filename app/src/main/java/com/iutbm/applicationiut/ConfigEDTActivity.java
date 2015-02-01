package com.iutbm.applicationiut;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import com.iutbm.applicationiut.edt.Group;
import com.iutbm.applicationiut.edt.MyExpandableListAdapter;

import java.util.ArrayList;


public class ConfigEDTActivity extends Activity {

    MyExpandableListAdapter adapter;
    SparseArray<Group> groups = new SparseArray<Group>();
    ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_edt);

        createData();
        listView = (ExpandableListView) findViewById(R.id.expandableListView);
        adapter = new MyExpandableListAdapter(this,groups);
        listView.setAdapter(adapter);

    }

    public void createData() {
        Group LPSIL = new Group("LP SIL TeProW");
        LPSIL.children.add("CAM-1");
        groups.append(0,LPSIL);

        Group DUTINFO = new Group("DUT Informatique");
        DUTINFO.children.add("S1 A1");
        DUTINFO.children.add("S1 A2");
        DUTINFO.children.add("S1 B1");
        DUTINFO.children.add("S1 B2");
        DUTINFO.children.add("S1 C1");
        DUTINFO.children.add("S1 C2");
        DUTINFO.children.add("S1 D1");
        DUTINFO.children.add("S1 D2");
        DUTINFO.children.add("S1 BIS A1");
        DUTINFO.children.add("S1 BIS A2");
        DUTINFO.children.add("S2 A1");
        DUTINFO.children.add("S2 A2");
        DUTINFO.children.add("S2 B1");
        DUTINFO.children.add("S2 B2");
        DUTINFO.children.add("S2 C1");
        DUTINFO.children.add("S2 C2");
        DUTINFO.children.add("S2 D1");
        DUTINFO.children.add("S2 D2");
        DUTINFO.children.add("S2 BIS A1");
        DUTINFO.children.add("S2 BIS A2");
        DUTINFO.children.add("S3 A1");
        DUTINFO.children.add("S3 A2");
        DUTINFO.children.add("S3 B1");
        DUTINFO.children.add("S3 B2");
        DUTINFO.children.add("S3 C1");
        DUTINFO.children.add("S3 C2");
        DUTINFO.children.add("S3 D1");
        DUTINFO.children.add("S3 D2");
        DUTINFO.children.add("S3 BIS A1");
        DUTINFO.children.add("S3 BIS A2");
        DUTINFO.children.add("S4 A1");
        DUTINFO.children.add("S4 A2");
        DUTINFO.children.add("S4 B1");
        DUTINFO.children.add("S4 B2");
        DUTINFO.children.add("S4 BIS A1");
        DUTINFO.children.add("S4 BIS A2");
        groups.append(1,DUTINFO);


//_______________________________________________________________________________


        Group DUTCS = new Group("DUT Carrières sociales 1e année (1)");
        DUTCS.children.add("TD1 GR Animation CS1 1.1");
        DUTCS.children.add("TD1 GR Animation CS1 1.2");
        groups.append(2,DUTCS);

        Group DUTCS1 = new Group("DUT Carrières sociales 1e année (2)");
        DUTCS1.children.add("TD2 GR Animation CS1 2.1");
        DUTCS1.children.add("TD2 GR Animation CS1 2.2");
        groups.append(3,DUTCS1);

        Group DUTCS2 = new Group("DUT Carrières sociales 1e année (TD)");
        DUTCS2.children.add("TD3 GR GU CS1 3.1");
        DUTCS2.children.add("TD3 GR GU CS1 3.2");
        groups.append(4,DUTCS2);

        Group DUTCS3 = new Group("DUT Carrières sociales 1e année (TD)");
        DUTCS3.children.add("TD4 GR SAP CS1 4.1");
        DUTCS3.children.add("TD4 GR SAP CS1 4.2");
        groups.append(5,DUTCS3);

        //_______________________________________________________________________________


        Group DUTCS4 = new Group("DUT Carrières sociales 2e année ");
        DUTCS4.children.add("GR Animation TD1 année 2 Anim 1 TP1");
        DUTCS4.children.add("GR Animation TD1 année 2 Anim 1 TP2");
        groups.append(6,DUTCS4);

        Group DUTCS5 = new Group("DUT Carrières sociales 2e année ");
        DUTCS5.children.add("GR Animation TD2 année 2 Anim 2 TP1");
        DUTCS5.children.add("GR Animation TD2 année 2 Anim 2 TP2");
        groups.append(7,DUTCS5);

        Group DUTCS6 = new Group("DUT Carrières sociales 2e année ");
        DUTCS6.children.add("GR GU TD année 2 GU TP1");
        DUTCS6.children.add("GR GU TD année 2 GU TP2");
        groups.append(8,DUTCS6);

        Group DUTCS7 = new Group("DUT Carrières sociales 2e année ");
        DUTCS7.children.add("GR SAP TD4 année 2 SAP TD1");
        DUTCS7.children.add("GR SAP TD4 année 2 SAP TD2");
        groups.append(9,DUTCS7);

        //_______________________________________________________________________________

        Group DUTGACO1 = new Group("DUT GACO 1e Année");
        DUTGACO1.children.add("APPRENTIS 1 APP A");
        DUTGACO1.children.add("APPRENTIS 1 APP B");
        groups.append(10,DUTGACO1);

        Group DUTGACO2 = new Group("DUT GACO 1e Année");
        DUTGACO2.children.add("INITIAUX 1 TP A");
        DUTGACO2.children.add("INITIAUX 1 TP B");
        groups.append(11,DUTGACO2);

        Group DUTGACO3 = new Group("DUT GACO 1e Année");
        DUTGACO3.children.add("INITIAUX 1 TP C");
        DUTGACO3.children.add("INITIAUX 1 TP D");
        groups.append(12,DUTGACO3);

        //_______________________________________________________________________________

        Group DUTGACO4 = new Group("DUT GACO 2eme Année");
        DUTGACO4.children.add("APPRENTIS 2 APP A");
        DUTGACO4.children.add("APPRENTIS 2 APP B");
        groups.append(13,DUTGACO4);

        Group DUTGACO5 = new Group("DUT GACO 2eme Année");
        DUTGACO5.children.add("INITIAUX 2 TP A");
        DUTGACO5.children.add("INITIAUX 2 TP B");
        groups.append(14,DUTGACO5);

        Group DUTGACO6 = new Group("DUT GACO 2eme Année");
        DUTGACO6.children.add("INITIAUX 2 TP C");
        DUTGACO6.children.add("INITIAUX 2 TP D");
        groups.append(15,DUTGACO6);

        //_________________________________________________________________________________

        Group DUTGC = new Group("DUT GC1 S1 ");
        DUTGC.children.add("TP A1");
        DUTGC.children.add("TP A2");
        groups.append(16,DUTGC);

        Group DUTGC1 = new Group("DUT GC1 S1 ");
        DUTGC1.children.add("TP B3");
        DUTGC1.children.add("TP B4");
        groups.append(17,DUTGC1);

        Group DUTGC2 = new Group("DUT GC1 S1 ");
        DUTGC2.children.add("TP C5");
        groups.append(18,DUTGC2);

        Group DUTGC3 = new Group("DUT GC1 S2 ");
        DUTGC3.children.add("TP A1");
        DUTGC3.children.add("TP A2");
        groups.append(19,DUTGC3);

        Group DUTGC4 = new Group("DUT GC1 S2 ");
        DUTGC4.children.add("TP B3");
        DUTGC4.children.add("TP B4");
        groups.append(20,DUTGC4);

        //_________________________________________________________________________________


        Group DUTGC5 = new Group("DUT GC2 S3 ");
        DUTGC5.children.add("TP D6");
        DUTGC5.children.add("TP D7");
        groups.append(21,DUTGC5);

        Group DUTGC6 = new Group("DUT GC2 S3 ");
        DUTGC6.children.add("TP D7");
        DUTGC6.children.add("TP D8");
        groups.append(22,DUTGC6);

        Group DUTGC7 = new Group("DUT GC2 S4 ");
        DUTGC7.children.add("TP IP");
        DUTGC7.children.add("TP PE");
        groups.append(23,DUTGC7);

        //____________________________________________________________________________________

        Group DUTGEII = new Group("DUT GEII 1e Année");
        DUTGEII.children.add("TP A1");
        DUTGEII.children.add("TP A2");
        groups.append(24,DUTGEII);

        Group DUTGEII1 = new Group("DUT GEII 1e Année");
        DUTGEII1.children.add("TP B3");
        DUTGEII1.children.add("TP B4");
        groups.append(25,DUTGEII1);

        //___________________________________________________________________________________

        Group DUTGEII2 = new Group("DUT GEII 2eme Année");
        DUTGEII2.children.add("TP A1");
        DUTGEII2.children.add("TP A2");
        groups.append(26,DUTGEII2);

        Group DUTGEII3 = new Group("DUT GEII 2eme Année");
        DUTGEII3.children.add("TP B3");
        DUTGEII3.children.add("TP B4");
        groups.append(27,DUTGEII3);

        //___________________________________________________________________________________

        Group DUTMMI = new Group("DUT MMI 1e année");
        DUTMMI.children.add("MMI1 TP A1");
        DUTMMI.children.add("MMI1 TP A2");
        groups.append(28,DUTMMI);

        Group DUTMMI1 = new Group("DUT MMI 1e année");
        DUTMMI1.children.add("MMI1 TP B1");
        DUTMMI1.children.add("MMI1 TP B2");
        groups.append(29,DUTMMI1);

        Group DUTMMI2 = new Group("DUT MMI 1e année");
        DUTMMI2.children.add("MMI1 TP C1");
        DUTMMI2.children.add("MMI1 TP C2");
        groups.append(30,DUTMMI2);

        //____________________________________________________________________________________

        Group DUTMMI3 = new Group("DUT MMI 2eme année");
        DUTMMI3.children.add("MMI2 TP A1");
        DUTMMI3.children.add("MMI2 TP A2");
        groups.append(31,DUTMMI3);

        Group DUTMMI4 = new Group("DUT MMI 2eme année");
        DUTMMI4.children.add("MMI2 TP B1");
        DUTMMI4.children.add("MMI2 TP B2");
        groups.append(32,DUTMMI4);

        Group DUTMMI5 = new Group("DUT MMI 2eme année");
        DUTMMI5.children.add("MMI2 TP C1");
        DUTMMI5.children.add("MMI2 TP C2");
        groups.append(33,DUTMMI5);

        //_____________________________________________________________________________________

        Group DUTGTE = new Group("DUT GTE - Génie thermique et énergie 1e année");
        DUTGTE.children.add("A");
        DUTGTE.children.add("B1");
        groups.append(34,DUTGTE);

        Group DUTGTE1 = new Group("DUT GTE - Génie thermique et énergie 1e année");
        DUTGTE1.children.add("B2");
        DUTGTE1.children.add("C");
        groups.append(35,DUTGTE1);

        Group DUTGTE2 = new Group("DUT GTE - Génie thermique et énergie 1e année");
        DUTGTE2.children.add("D2");
        groups.append(36,DUTGTE2);

        Group DUTGTE3 = new Group("DUT GTE - Génie thermique et énergie 1e année");
        DUTGTE3.children.add("D1");
        DUTGTE3.children.add("E");
        groups.append(37,DUTGTE3);

        //___________________________________________________________________________________

        Group DUTGTE4 = new Group("DUT GTE - Génie thermique et énergie 2eme année");
        DUTGTE4.children.add("3A");
        DUTGTE4.children.add("3B");
        groups.append(38,DUTGTE4);

        Group DUTGTE5 = new Group("DUT GTE - Génie thermique et énergie 2eme année");
        DUTGTE5.children.add("3D");
        DUTGTE5.children.add("3E");
        groups.append(39,DUTGTE5);

        Group DUTGTE6 = new Group("DUT GTE - Génie thermique et énergie 2eme année");
        DUTGTE6.children.add("4A");
        DUTGTE6.children.add("4B");
        groups.append(40,DUTGTE6);

        Group DUTGTE7 = new Group("DUT GTE - Génie thermique et énergie 2eme année");
        DUTGTE7.children.add("4D");
        DUTGTE7.children.add("4E");
        groups.append(41,DUTGTE7);

        //____________________________________________________________________________________

        Group DUTMP = new Group("DUT Mesures physiques 1e année ");
        DUTMP.children.add("A1");
        DUTMP.children.add("A2");
        groups.append(42,DUTMP);

        Group DUTMP1 = new Group("DUT Mesures physiques 1e année ");
        DUTMP1.children.add("B1");
        DUTMP1.children.add("B2");
        groups.append(43,DUTMP1);

        Group DUTMP2 = new Group("DUT Mesures physiques 1e année ");
        DUTMP2.children.add("C1");
        DUTMP2.children.add("C2");
        groups.append(44,DUTMP2);

        //____________________________________________________________________________________

        Group DUTMP3 = new Group("DUT Mesures physiques 2eme année ");
        DUTMP3.children.add("A1");
        DUTMP3.children.add("A2");
        groups.append(45,DUTMP3);

        Group DUTMP4 = new Group("DUT Mesures physiques 2eme année ");
        DUTMP4.children.add("B1");
        DUTMP4.children.add("B2");
        groups.append(46,DUTMP4);

        Group DUTMP5 = new Group("DUT Mesures physiques 2eme année ");
        DUTMP5.children.add("C1");
        DUTMP5.children.add("C2");
        groups.append(47,DUTMP5);

        //_____________________________________________________________________________________

        Group DUTRT = new Group("DUT RT - Réseaux et télécommunications 1e année ");
        DUTRT.children.add("GB1");
        DUTRT.children.add("GB2");
        groups.append(48,DUTRT);

        Group DUTRT1 = new Group("DUT RT - Réseaux et télécommunications 1e année ");
        DUTRT1.children.add("LK1");
        DUTRT1.children.add("LK2");
        groups.append(49,DUTRT1);

        Group DUTRT2 = new Group("DUT RT - Réseaux et télécommunications 1e année ");
        DUTRT2.children.add("A1");
        groups.append(50,DUTRT2);

        //_____________________________________________________________________________________

        Group DUTRT3 = new Group("DUT RT - Réseaux et télécommunications 2eme année ");
        DUTRT3.children.add("IPI1");
        DUTRT3.children.add("IPI2");
        groups.append(51,DUTRT3);

        Group DUTRT4 = new Group("DUT RT - Réseaux et télécommunications 2eme année ");
        DUTRT4.children.add("SP1");
        DUTRT4.children.add("SP2");
        groups.append(52,DUTRT4);

        Group DUTRT5 = new Group("DUT RT - Réseaux et télécommunications 2eme année ");
        DUTRT5.children.add("PEL");
        groups.append(53,DUTRT5);

        Group DUTRT6 = new Group("DUT RT - Réseaux et télécommunications 2eme année ");
        DUTRT6.children.add("ALT2");
        groups.append(53,DUTRT6);

        //_____________________________________________________________________________________

        Group LPCTPEB = new Group("LP_CTPEB ");
        LPCTPEB.children.add("Parcours_CT");
        LPCTPEB.children.add("Parcours_PEB");
        groups.append(54,LPCTPEB);

        Group LPADIO = new Group("LP_CTPEB ");
        LPADIO.children.add("ADIO allemand");
        LPADIO.children.add("ADIO espagnol");
        groups.append(55,LPADIO);

        Group LPCART = new Group("LP_CART ");
        LPCART.children.add("CART 1");
        LPCART.children.add("CART 2");
        groups.append(56,LPCART);

        Group LPCIM = new Group("LP_CIM ");
        LPCIM.children.add("CIM A");
        LPCIM.children.add("CIM B");
        groups.append(57,LPCIM);

        Group LPDORA = new Group("LP_DORA ");
        LPDORA.children.add("LP-DORA");
        LPDORA.children.add("LP-DORA CM");
        groups.append(58,LPDORA);

        Group LPDORA1 = new Group("LP_DORA ");
        LPDORA1.children.add("LP-DORA TD");
        LPDORA1.children.add("LP-DORA TP");
        groups.append(59,LPDORA1);

        Group LPDORA2 = new Group("LP_DORA ");
        LPDORA2.children.add("LP-DORA TP A");
        LPDORA2.children.add("LP-DORA TP B");
        groups.append(60,LPDORA2);

        Group LPENR = new Group("Lp EnR - Energie, génie climatique, énergies renouvelables ");
        LPENR.children.add("A");
        LPENR.children.add("B");
        groups.append(61,LPENR);

        Group LPISASS= new Group("Lp IS-ASS - Animation sociale et socio-culturelle");
        LPISASS.children.add("Groupe 1");
        LPISASS.children.add("Groupe 2");
        groups.append(62,LPISASS);

        Group LPISFVPI= new Group("Lp IS-FVPI - Famille vieillissement problématiques intergénération. ");
        LPISFVPI.children.add("GR FAMILLE");
        LPISFVPI.children.add("GR VIEILLISSEMENT");
        groups.append(63,LPISFVPI);

        Group LPMOSEL= new Group("Lp MOSEL ");
        LPMOSEL.children.add("TD1");
        LPMOSEL.children.add("TD2");
        groups.append(64,LPMOSEL);

        Group LPMOSEL1= new Group("Lp MOSEL ");
        LPMOSEL1.children.add("Soutenances Stage MOSEL");
        groups.append(65,LPMOSEL1);

        Group LPTICAMACO= new Group("Lp TICAMACO - Commerce, tic appliquées au marketing et au commerce");
        LPTICAMACO.children.add("LP Initiaux");
        LPTICAMACO.children.add("LP apprentis TIC@MACO");
        groups.append(66,LPTICAMACO);

        Group LPVEGA= new Group("Lp VEGA - Véhicules : électronique et gestion des automatismes");
        LPVEGA.children.add("VEGA_TP1");
        LPVEGA.children.add("VEGA_TP2");
        groups.append(67,LPVEGA);

        Group LPWD= new Group("Lp WD - Webdesign ");
        LPWD.children.add("LP WD ALT1");
        LPWD.children.add("LP WD ALT1");
        groups.append(68,LPWD);

        Group LPWD1= new Group("Lp WD - Webdesign ");
        LPWD1.children.add("LP WD INI1");
        LPWD1.children.add("LP WD INI2");
        groups.append(69,LPWD1);


    }

}
