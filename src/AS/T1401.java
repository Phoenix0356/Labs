package AS;

import java.util.Scanner;

public class T1401 {
   static int[][] board ;
   static int num=1;
   static void fillBoard(int zeroX,int zeroY,int ox,int oy,int newSize){
      if (newSize==2){
         board[ox][oy]=num;
         board[ox+1][oy]=num;
         board[ox][oy+1]=num;
         board[ox+1][oy+1]=num;
         board[ox+zeroX][oy+zeroY]=0;
         num++;
         return;
      }
      int size=newSize/2;
         if (zeroX<size&&zeroY<size){
            fillBoard(zeroX,zeroY,ox,oy,size);
            fillBoard(0,size-1,ox+size,oy,size);
            fillBoard(size-1,0,ox,oy+size,size);
            fillBoard(0,0,ox+size,oy+size,size);
         }else if (zeroX>=size&&zeroY<size){
            fillBoard(size-1,size-1,ox,oy,size);
            fillBoard(zeroX-size,zeroY,ox+size,oy,size);
            fillBoard(size-1,0,ox,oy+size,size);
            fillBoard(0,0,ox+size,oy+size,size);
         }else if (zeroX<size){
            fillBoard(size-1,size-1,ox,oy,size);
            fillBoard(0,size-1,ox+size,oy,size);
            fillBoard(zeroX,zeroY-size,ox,oy+size,size);
            fillBoard(0,0,ox+size,oy+size,size);
         }else {
            fillBoard(size-1,size-1,ox,oy,size);
            fillBoard(0,size-1,ox+size,oy,size);
            fillBoard(size-1,0,ox,oy+size,size);
            fillBoard(zeroX-size,zeroY-size,ox+size,oy+size,size);
         }
   }
   public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n= sc.nextInt();
      int zx=sc.nextInt()-1;
      int zy=sc.nextInt()-1;
      int size=(int)Math.pow(2,n);
      board=new int[size][size];
      fillBoard(zx,zy,0,0,size);
      board[zx][zy]=-1;
      for(int i=0;i<size;i++){
         for (int j=0;j<size;j++) {
            System.out.print("\t"+board[i][j]+" ");
         }
         System.out.println();
      }
      System.out.println();
      for(int i=0;i<size;i++){
         for (int j=0;j<size;j++){
            if (board[i][j]==0){

               board[i][j]=num;
               if (board[i+1][j]==0)board[i+1][j]=num;
               if (board[i+1][j+1]==0)board[i+1][j+1]=num;
               if (board[i+1][j-1]==0)board[i+1][j-1]=num;
               if (board[i][j+1]==0)board[i][j+1]=num;
               num++;
            }
         }
      }
      board[zx][zy]=0;
      for(int i=0;i<size;i++){
         for (int j=0;j<size;j++) {
            System.out.print("\t"+board[i][j]+" ");
         }
         System.out.println();
      }
   }
}
