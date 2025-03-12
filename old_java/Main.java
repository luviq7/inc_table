import java.util.Scanner;
public class Main {
    public static boolean isValid(String stra) {
        int balance = 0;
        for (char ch : stra.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
    public static int openDock(String stra) {
        int b = 0;
        for (char ch : stra.toCharArray()) {
            if (ch == '(') {
                b++;
            }
        }
        return b;
    }
    public static int calcOperations(String s) {
        int suma = 0;
        for(int asd = 0; asd < s.length(); asd++) {
            if(s.charAt(asd) == '<' && s.charAt(asd + 1) == '-' && s.charAt(asd + 2) == '>') {
                suma++;
                asd = asd + 2;
            }
            else if( s.charAt(asd ) == '-' && s.charAt(asd + 1) == '>') {
                suma++;
            }
            else if(s.charAt(asd) == '!') {
                suma++;
            }
            else if(s.charAt(asd) == '&' && s.charAt(asd + 1) == '&') {
                suma++;
            }
            else if(s.charAt(asd) == '|' && s.charAt(asd + 1) == '|') {
                suma++;
            }
        }
        return suma;
    }
    public static String deleteCharacters(String str, int from, int to) {
        return str.substring(0,from)+str.substring(to);
    }
    public static String addCharacters(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }

    public static void main(String[] args) {
        boolean[] P = {false,false,false,false,true,true,true,true};
        boolean[] Q = {false,false,true,true,false,false,true,true};
        boolean[] R = {false,true,false,true,false,true,false,true};


        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();


        String str1 = str;
        String strCheck1 = "";
        String strCheck2 = "";

        int opera = calcOperations(str);



        int w = 0,h = 0;
        String[] mass;
        mass = new String[openDock(str)+1];
        char[] massLetter;
        massLetter = new char[openDock(str)+1];
        boolean[][] Finaltable;
        Finaltable = new boolean[openDock(str)+1][8];



        if(isValid(str)) {
            for(int qw = 0; qw < opera; qw++) {

                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '(') {
                        for (int j = i + 1; j < str.length(); j++) {
                            if (str.charAt(j) == '(') {
                                break;
                            }
                            else if (str.charAt(j) == ')') {
                                String sub = "";
                                if(j-i==2){
                                    sub = (((str.substring(i-1, j + 1)).replaceAll("\s+","")).replaceAll("\\(","")).replaceAll("\\)","");
                                }
                                else{
                                    sub = (((str.substring(i, j + 1)).replaceAll("\s+","")).replaceAll("\\(","")).replaceAll("\\)","");
                                }
                                mass[h] = sub;
                                massLetter[h] = (char) (65 + w);

                                char chr = (char) (65 + w);
                                str = addCharacters(str, chr, j + 1);


                                if(j-i==2){
                                    str = deleteCharacters(str, i-1, j + 1);
                                }
                                else {
                                    str = deleteCharacters(str, i, j + 1);
                                }
                                for (int u = 0; j - i > u; u++) {
                                    str = addCharacters(str, ' ', i);
                                }


                                h++;
                                w++;
                                i = j + 1;
                                break;

                            }
                        }

                    }
                }
                if(qw%2==0){
                    strCheck1 = str;
                }
                else{
                    strCheck2 = str;
                }
                if(strCheck1.equals(strCheck2)){
                    break;
                }
            }
            mass[mass.length-1] = str.replaceAll("\s+","");
            massLetter[massLetter.length-1] = (char) (65 + w);

            for(int f1=0;Finaltable.length > f1;f1++){
                if(mass[f1].length() == 1){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh];
                        }

                }



                else if(mass[f1].charAt(0) == '!'){
                    if(mass[f1].charAt(1)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh];
                            }

                    }

                    else if(mass[f1].charAt(1)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !Q[gh];
                            }
                    }
                    else if(mass[f1].charAt(1)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !R[gh];
                            }


                    }
                    else{
                        int tr=(int)mass[f1].charAt(1)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = !Finaltable[tr][gh];
                        }
                    }
                }



                else if(mass[f1].charAt(1) == '-'){

                    if(mass[f1].charAt(0)=='p'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||Finaltable[tr][gh];
                            }
                        }
                    }

                    else if(mass[f1].charAt(0)=='q'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !Q[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !Q[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !Q[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !Q[gh]||Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(0)=='r'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !R[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !R[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !R[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !R[gh]||Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(3)=='p'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = !Finaltable[tr][gh]||P[gh];
                        }
                    }
                    else if(mass[f1].charAt(3)=='q'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = !Finaltable[tr][gh]||Q[gh];
                        }
                    }
                    else if(mass[f1].charAt(3)=='r'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = !Finaltable[tr][gh]||R[gh];
                        }
                    }
                    else{
                        int tr1=(int)mass[f1].charAt(0)-65;
                        int tr2=(int)mass[f1].charAt(3)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = !Finaltable[tr1][gh]||Finaltable[tr2][gh];
                        }
                    }

                }






                else if(mass[f1].charAt(1) == '<'){
                    if(mass[f1].charAt(0)=='p'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = !P[gh]||Finaltable[tr][gh];
                            }
                        }
                    }

                    else if(mass[f1].charAt(0)=='q'){
                        if(mass[f1].charAt(4)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]==P[gh];
                            }
                        }
                        else if(mass[f1].charAt(4)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]==Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(4)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]==R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(4)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]==Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(0)=='r'){
                        if(mass[f1].charAt(4)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]==P[gh];
                            }
                        }
                        else if(mass[f1].charAt(4)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]==Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(4)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]==R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(4)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]==Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(4)=='p'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]==P[gh];
                        }
                    }
                    else if(mass[f1].charAt(4)=='q'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]==Q[gh];
                        }
                    }
                    else if(mass[f1].charAt(4)=='r'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]==R[gh];
                        }
                    }
                    else{
                        int tr1=(int)mass[f1].charAt(0)-65;
                        int tr2=(int)mass[f1].charAt(4)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr1][gh]==Finaltable[tr2][gh];
                        }
                    }
                }



                else if(mass[f1].charAt(1) == '&'){
                    if(mass[f1].charAt(0)=='p'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]&&P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]&&Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]&&R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]&&Finaltable[tr][gh];
                            }
                        }
                    }

                    else if(mass[f1].charAt(0)=='q'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]&&P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]&&Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]&&R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]&&Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(0)=='r'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]&&P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]&&Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]&&R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]&&Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(3)=='p'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]&&P[gh];
                        }
                    }
                    else if(mass[f1].charAt(3)=='q'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]&&Q[gh];
                        }
                    }
                    else if(mass[f1].charAt(3)=='r'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]&&R[gh];
                        }
                    }
                    else{
                        int tr1=(int)mass[f1].charAt(0)-65;
                        int tr2=(int)mass[f1].charAt(3)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr1][gh]&&Finaltable[tr2][gh];
                        }
                    }
                }



                else if(mass[f1].charAt(1) == '|'){
                    if(mass[f1].charAt(0)=='p'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = P[gh]||Finaltable[tr][gh];
                            }
                        }
                    }

                    else if(mass[f1].charAt(0)=='q'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = Q[gh]||Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(0)=='r'){
                        if(mass[f1].charAt(3)=='p'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]||P[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='q'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]||Q[gh];
                            }
                        }
                        else if(mass[f1].charAt(3)=='r'){
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]||R[gh];
                            }
                        }
                        else{
                            int tr=(int)mass[f1].charAt(3)-65;
                            for(int gh= 0;8>gh;gh++){
                                Finaltable[f1][gh] = R[gh]||Finaltable[tr][gh];
                            }
                        }
                    }
                    else if(mass[f1].charAt(3)=='p'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]||P[gh];
                        }
                    }
                    else if(mass[f1].charAt(3)=='q'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]||Q[gh];
                        }
                    }
                    else if(mass[f1].charAt(3)=='r'){
                        int tr=(int)mass[f1].charAt(0)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr][gh]||R[gh];
                        }
                    }
                    else{
                        int tr1=(int)mass[f1].charAt(0)-65;
                        int tr2=(int)mass[f1].charAt(3)-65;
                        for(int gh= 0;8>gh;gh++){
                            Finaltable[f1][gh] = Finaltable[tr1][gh]||Finaltable[tr2][gh];
                        }
                    }
                }




            }
            for(int yu=0;mass.length>yu;yu++){
                System.out.printf("\t%s   \t",mass[yu]);
            }
            System.out.println();
            for(int yu=0;massLetter.length>yu;yu++){
                System.out.printf("\t%c    \t",massLetter[yu]);
            }
            System.out.println();
            for(int yu=0;8>yu;yu++){
                     for(int io=0;Finaltable.length>io;io++){
                         System.out.printf("\t%b\t",Finaltable[io][yu]);
                     }
                     System.out.println();
            }


        }
        else{
            System.out.println("Invalid Expression");
        }
    }
}