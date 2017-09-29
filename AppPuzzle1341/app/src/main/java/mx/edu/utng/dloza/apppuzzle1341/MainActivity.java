package mx.edu.utng.dloza.apppuzzle1341;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,
        AdapterView.OnItemSelectedListener,View.OnClickListener{
    private Button btnMezclar;
    private Button btnSalir;
    private Button btnCreditos;
    private Spinner cmbTipo;//tipo de rompecabezas: libre, clasico, circular
    private Spinner cmbOpciones;//imagenes seleccionadas
    private int tipo;
    private int img;
    private PuzzleEstrategia juego;
    private ImageButton[] imagenes;
    private int dispo=15;//casilla disponible en el modo clasico
    private int pos1;//saber la posicion uno de la pieza seleccionada en el libre
    private int pos2;//saber la posicion dos de la pieza seleccionada en el libre
    private boolean banPar=true;//se pulsaron 2 piezas
    private int x1;//coordenadas donde inicia el touch en el modo de circulo
    private int y1;
    private int x2;//coordenadas donde finaliza el touch
    private int y2;
    private int dxy;//direccion a la que se desplaza

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarControles();

        cmbTipo.setOnItemSelectedListener(this);
        cmbOpciones.setOnItemSelectedListener(this);

        //para que todos escuchen el mismoevento
        for(int i=0;i<16;i++){
            imagenes[i].setOnTouchListener(this);
        }

        btnMezclar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mezclar();

            }
        });

    }

    public void inicializarControles(){
        imagenes=new ImageButton[16];
        imagenes[0]=(ImageButton)findViewById(R.id.button1);
        imagenes[1]=(ImageButton)findViewById(R.id.button2);
        imagenes[2]=(ImageButton)findViewById(R.id.button3);
        imagenes[3]=(ImageButton)findViewById(R.id.button4);
        imagenes[4]=(ImageButton)findViewById(R.id.button5);
        imagenes[5]=(ImageButton)findViewById(R.id.button6);
        imagenes[6]=(ImageButton)findViewById(R.id.button7);
        imagenes[7]=(ImageButton)findViewById(R.id.button8);
        imagenes[8]=(ImageButton)findViewById(R.id.button9);
        imagenes[9]=(ImageButton)findViewById(R.id.button10);
        imagenes[10]=(ImageButton)findViewById(R.id.button11);
        imagenes[11]=(ImageButton)findViewById(R.id.button12);
        imagenes[12]=(ImageButton)findViewById(R.id.button13);
        imagenes[13]=(ImageButton)findViewById(R.id.button14);
        imagenes[14]=(ImageButton)findViewById(R.id.button15);
        imagenes[15]=(ImageButton)findViewById(R.id.button16);

        btnMezclar=(Button)findViewById(R.id.btn_mezclar);
        btnSalir=(Button)findViewById(R.id.btn_salir);
        cmbTipo=(Spinner)findViewById(R.id.spn_tipos);
        cmbOpciones=(Spinner)findViewById(R.id.spn_opciones);
        btnCreditos=(Button)findViewById(R.id.btn_creditos);
    }

    @Override
    public void onClick(View view) {

        btnCreditos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Créditos:" +
                                "\nDesarrollador: Victor Daniel Loza Cruz" +
                                "\nDesarrollador Master: Apolinar Trejo Cuevas" +
                                "\nAsignatura: Desarrollo de Aplicaciones II" +
                                "\nÁrea: Sistemas Informáticos" +
                                "\nCampus: Universidad Tecnológica del Norte de Guanajuato"
                        ,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(adapterView.getId()==R.id.spn_tipos){
            tipo=(int)cmbTipo.getSelectedItemId();
            switch(tipo){
                case 1:
                    juego= new PuzzleLibre();
                    break;
                case 2:
                    juego=new PuzzleClasico();
                    break;
                case 3:
                    juego=new PuzzleCircular();
                    break;
            }
        }else if(adapterView.getId()==R.id.spn_opciones){
            img= (int)cmbOpciones.getSelectedItemId();
            cargarImagens(img);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void cargarImagens(int img){
        switch (img){
            case 1://numeros
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.n1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.n2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.n3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.n4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.n5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.n6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.n7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.n8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.n9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.n10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.n11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.n12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.n13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.n14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.n15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.n16));
                break;
            case 2://android
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.a1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.a2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.a3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.a4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.a5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.a6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.a7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.a8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.a9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.a10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.a11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.a12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.a13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.a14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.a15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.a16));
                break;
            case 3://goku
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.go1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.go2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.go3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.go4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.go5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.go6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.go7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.go8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.go9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.go10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.go11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.go12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.go13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.go14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.go15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.go16));
                break;
            case 4://DBZ
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.g1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.g2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.g3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.g4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.g5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.g6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.g7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.g8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.g9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.g10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.g11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.g12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.g13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.g14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.g15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.g16));
                break;
            case 5://mario
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.m1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.m2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.m3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.m4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.m5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.m6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.m7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.m8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.m9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.m10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.m11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.m12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.m13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.m14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.m15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.m16));
                break;
            case 6://Payaso
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.p1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.p2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.p3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.p4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.p5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.p6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.p7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.p8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.p9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.p10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.p11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.p12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.p13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.p14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.p15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.p16));
                break;
            case 7://Sombrerero
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.s5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.s6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.s7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.s8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.s9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.s10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.s11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.s12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.s13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.s14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.s15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.s16));
                break;
            case 8://Favorita
                imagenes[0].setBackgroundDrawable(getResources().getDrawable(R.drawable.f1));
                imagenes[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.f2));
                imagenes[2].setBackgroundDrawable(getResources().getDrawable(R.drawable.f3));
                imagenes[3].setBackgroundDrawable(getResources().getDrawable(R.drawable.f4));
                imagenes[4].setBackgroundDrawable(getResources().getDrawable(R.drawable.f5));
                imagenes[5].setBackgroundDrawable(getResources().getDrawable(R.drawable.f6));
                imagenes[6].setBackgroundDrawable(getResources().getDrawable(R.drawable.f7));
                imagenes[7].setBackgroundDrawable(getResources().getDrawable(R.drawable.f8));
                imagenes[8].setBackgroundDrawable(getResources().getDrawable(R.drawable.f9));
                imagenes[9].setBackgroundDrawable(getResources().getDrawable(R.drawable.f10));
                imagenes[10].setBackgroundDrawable(getResources().getDrawable(R.drawable.f11));
                imagenes[11].setBackgroundDrawable(getResources().getDrawable(R.drawable.f12));
                imagenes[12].setBackgroundDrawable(getResources().getDrawable(R.drawable.f13));
                imagenes[13].setBackgroundDrawable(getResources().getDrawable(R.drawable.f14));
                imagenes[14].setBackgroundDrawable(getResources().getDrawable(R.drawable.f15));
                imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.f16));
                break;
        }
        if (tipo==2){//tipo clasico
            imagenes[15].setBackgroundDrawable(getResources().getDrawable(R.drawable.vacio));
        }
    }

    private void mezclar(){
        int x;
        int y;
        Random r= new Random();
        for(int i=0;i<150;i++){
            x=r.nextInt(16);
            y=r.nextInt(16);
            if(juego.verificarMov(x)){
                switch(tipo){
                    case 1:
                        juego.moverPieza(x,y);
                        moverImagenes(x,y);
                        break;
                    case 2:
                        juego.moverPieza(x,dispo);
                        moverImagenes(x,dispo);
                        break;
                    case 3:
                        juego.moverPieza(x,y%4);
                        moverImagenes(x,y%4);
                        break;
                }
            }
        }
    }

    public void moverImagenes(int x, int y){

        ImageButton tempo=new  ImageButton(this);
        switch(tipo){
            case 2:
                dispo=x;
            case 1:
                tempo.setBackgroundDrawable(imagenes[x].getBackground());
                imagenes[x].setBackgroundDrawable(imagenes[y].getBackground());
                imagenes[y].setBackgroundDrawable(tempo.getBackground());
                break;
            case 3:
                int i=x/4;//fila
                int j=x%4;//columna
                switch (y){
                    case 1://Arriba
                        tempo.setBackgroundDrawable(imagenes[j].getBackground());
                        imagenes[j].setBackgroundDrawable(imagenes[j+4].getBackground());
                        imagenes[j+4].setBackgroundDrawable(imagenes[j+8].getBackground());
                        imagenes[j+8].setBackgroundDrawable(imagenes[j+12].getBackground());
                        imagenes[j+12].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 2://derecha
                        tempo.setBackgroundDrawable(imagenes[i*4+3].getBackground());
                        imagenes[i*4+3].setBackgroundDrawable(imagenes[i*4+2].getBackground());
                        imagenes[i*4+2].setBackgroundDrawable(imagenes[i*4+1].getBackground());
                        imagenes[i*4+1].setBackgroundDrawable(imagenes[i*4].getBackground());
                        imagenes[i*4].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 3://abajo
                        tempo.setBackgroundDrawable(imagenes[j+12].getBackground());
                        imagenes[j+12].setBackgroundDrawable(imagenes[j+8].getBackground());
                        imagenes[j+8].setBackgroundDrawable(imagenes[j+4].getBackground());
                        imagenes[j+4].setBackgroundDrawable(imagenes[j].getBackground());
                        imagenes[j].setBackgroundDrawable(tempo.getBackground());
                        break;
                    case 4://izquierda
                        tempo.setBackgroundDrawable(imagenes[i*4].getBackground());
                        imagenes[i*4].setBackgroundDrawable(imagenes[i*4+1].getBackground());
                        imagenes[i*4+1].setBackgroundDrawable(imagenes[i*4+2].getBackground());
                        imagenes[i*4+2].setBackgroundDrawable(imagenes[i*4+3].getBackground());
                        imagenes[i*4+3].setBackgroundDrawable(tempo.getBackground());
                        break;
                }
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int dx=0;//desplazamiento horizontal
        int dy=0;//desplazamiento vertical
        switch(view.getId()){
            case R.id.button1:
                    pos1=0;
                break;
            case R.id.button2:
                    pos1=1;
                break;
            case R.id.button3:
                pos1=2;
                break;
            case R.id.button4:
                pos1=3;
                break;
            case R.id.button5:
                pos1=4;
                break;
            case R.id.button6:
                pos1=5;
                break;
            case R.id.button7:
                pos1=6;
                break;
            case R.id.button8:
                pos1=7;
                break;
            case R.id.button9:
                pos1=8;
                break;
            case R.id.button10:
                pos1=9;
                break;
            case R.id.button11:
                pos1=10;
                break;
            case R.id.button12:
                pos1=11;
                break;
            case R.id.button13:
                pos1=12;
                break;
            case R.id.button14:
                pos1=13;
                break;
            case R.id.button15:
                pos1=14;
                break;
            case R.id.button16:
                pos1=15;
                break;
        }
        switch(tipo){
            case 1: //libre
                banPar=!banPar;
                if(!banPar){//si es falsa apenas ha seleccionado una pieza
                    pos2=pos1;
                }else{//se seleccionaron 2 piezas
                    juego.moverPieza(pos1,pos2);
                    moverImagenes(pos1,pos2);
                    if(juego.yaGano()){
                        Toast.makeText(getApplicationContext(),
                                "Felicidades Ganaste!!",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 2://clasico
                if (juego.verificarMov(pos1)) {
                    juego.moverPieza(pos1, dispo);
                    moverImagenes(pos1, dispo);
                    if (juego.yaGano()) {
                        Toast.makeText(getApplicationContext(),
                                "Felicidades Ganaste!!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case 3://circulo
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    x1=(int)motionEvent.getX();//obteniendo las coordenadas
                    y1=(int)motionEvent.getY();//del inicio del touch
                }
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    x1=(int)motionEvent.getX();//obteniendo las coordenadas
                    y1=(int)motionEvent.getY();//del fin del touch
                    dx=Math.abs(x2-x1);
                    dy=Math.abs(y2-y1);
                    if(dx>dy) {//movimiento horizontal
                        if(x2>x1){//desplazamiento a la derecha
                            dxy=2;
                        }else{//movimiento a la izq.
                            dxy=4;
                        }
                    }else {//movimiento vertical
                        if(y2>y1){//movimiento hacia abajo
                            dxy=3;
                        }else{//movimiento hacia arriba
                            dxy=1;
                        }
                    }
                    juego.moverPieza(pos1,dxy);
                    moverImagenes(pos1,dxy);
                    if (juego.yaGano()) {
                        Toast.makeText(getApplicationContext(),
                                "Felicidades Ganaste!!", Toast.LENGTH_LONG).show();
                    }
                }
        }
        return false;

    }
}
