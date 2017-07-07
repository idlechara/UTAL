package ann;

import engine.Board;
import engine.Coord;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Emmanuel on 17/10/2015.
 * TODO: Verificar si es necesario normalizar
 */
public class Feature {

    //Features by piece
    public double weights[];

    public Feature(){
        weights = new double[10];
        this.weights[0] = 0.00900;
        this.weights[1] = 0.00500;
        this.weights[2] = 0.00340;
        this.weights[3] =  0.00330;
        this.weights[4] =  0.00330;
        this.weights[5] = 0.0015;
        this.weights[6] = 0.0015;
        this.weights[7] = 0.0015;
        this.weights[8] = 0.0015;
        this.weights[9] = 0.0015;
    }



    public ArrayList turnEvalutation(Board b){
        if(b.turn == 1)
            return b.getWhitePieces();
        else
            return b.getBlackPieces();
    }

    public ArrayList getFeatures(Board b){
        ArrayList caracteristicas = new ArrayList();
        caracteristicas.add(firstF(b, 1));
        caracteristicas.add(firstF(b, 2));
        caracteristicas.add(firstF(b, 3));
        caracteristicas.add(firstF(b, 4));
        caracteristicas.add(firstF(b, 5));
        caracteristicas.add(rook_openfile(b));
        caracteristicas.add(knight_mobility(b));
        caracteristicas.add(promotion_pawn(b));
        caracteristicas.add(rocksuport_pawn(b));
        return caracteristicas;
    }

    public void modifyW(int i, int v){
            this.weights[i] = v;
    }

    public double firstF(Board b,int piece){
        int contador = 0;
        ArrayList<Coord> lista_piezas = turnEvalutation(b);

        for(int i = 0; i < lista_piezas.size(); i++){
            if(b.getPiece(lista_piezas.get(i))== b.turn*piece)
                contador++;
        }
        switch(piece){
            case 1:  return contador*weights[0];
            case 2:  return contador*weights[1];
            case 3:  return contador*weights[2];
            case 4:  return contador*weights[3];
            case 5:  return contador*weights[4];
            default :return 0;

        }


    }

    public double rook_openfile(Board b){
        boolean flag;
        ArrayList<Coord> lista_piezas = turnEvalutation(b);
        for(int i = 0; i < lista_piezas.size(); i++){

            if(b.getPiece(lista_piezas.get(i)) ==  b.turn*4){
                int x = lista_piezas.get(i).y;

                flag = true;
                for(int j = 0; j < 8; j++){
                    //System.out.println(b.getPiece(new Coord(j,x)) + " " + (b.getPiece(new Coord(j,x)) == 0 || b.getPiece(new Coord(j,x)) == b.turn*4));
                    if(b.getPiece(new Coord(j,x)) == 0 || b.getPiece(new Coord(j,x)) == b.turn*4)
                        flag = true;
                    else
                        return 0;

                }

                if(flag)
                    return this.weights[5];
            }
        }

        return 0;
    }

    public double knight_mobility(Board b){
        ArrayList<Coord> lista_piezas = turnEvalutation(b);
        int contador = 0;
        for(int i = 0; i < lista_piezas.size(); i++){
            if(b.getPiece(lista_piezas.get(i)) == b.turn*2){
                Stack<Coord> movimientos = new Stack<Coord>();
                int x = lista_piezas.get(i).x;
                int y = lista_piezas.get(i).y;
                movimientos.push(new Coord(x + 2, y - 1));
                movimientos.push(new Coord(x + 2, y + 1));
                movimientos.push(new Coord(x + 1, y + 2));
                movimientos.push(new Coord(x - 1, y + 2));
                movimientos.push(new Coord(x - 2, y + 1));
                movimientos.push(new Coord(x - 2, y - 1));
                movimientos.push(new Coord(x + 1, y - 2));
                movimientos.push(new Coord(x - 1, y - 2));
                int piezao = b.getPiece(lista_piezas.get(i));
                int tam = movimientos.size();
                for(int j = 0; j <tam; j++){
                    Coord c = movimientos.pop();

                    if(c.x >= 0 && c.x < 8 && c.y >= 0 && c.y < 8){
                        int piezad = b.getPiece(c);
                        //System.out.println(piezad);
                        if(piezad == 0 || (piezao  > 0 && piezad < 0) || (piezao  < 0 && piezad > 0))
                            contador++;
                    }

                }



            }

        }
        if(contador > 6)
            return weights[6];
        return 0;
    }

    public double promotion_pawn(Board b){

        ArrayList<Coord> lista_piezas = turnEvalutation(b);

        for(int i = 0; i < lista_piezas.size(); i++){
            Coord c = lista_piezas.get(i);
            int piece = b.getPiece(c);

            if(piece == 1 && c.x ==0) {
                return this.weights[8];
            }
            else if(piece == -1 && c.x == 7){
                return this.weights[8];
            }



        }
        return 0;

    }

    public double rocksuport_pawn(Board b1){
        ArrayList<Coord> lista_piezas1 = turnEvalutation(b1);
        Board b2 = b1.clone();
        b2.turn = -1*b1.turn;
        ArrayList<Coord> lista_piezas2 = turnEvalutation(b2);

        for(int i = 0; i < lista_piezas1.size(); i++){
            Coord c1 = lista_piezas1.get(i);

            if(b1.getPiece(c1) == b1.turn*1 && ((b1.getPiece(c1) == 1 && c1.x == 1 && b1.getPiece(new Coord(c1.x-1,c1.y)) == 0) || (b1.getPiece(c1) == -1 && c1.x == 6 && b1.getPiece(new Coord(c1.x+1,c1.y)) == 0))){

                for(int j = 0; j < lista_piezas2.size(); j++){
                    Coord c2 = lista_piezas2.get(j);

                    if(b2.getPiece(c2) == b2.turn*4 && ((b2.getPiece(c2) == 4 && c2.x == 7) || (b2.getPiece(c2) == -4 && c2.x == 0))){
                        return this.weights[9];
                    }
                }
            }


        }
        return 0;

    }
}
