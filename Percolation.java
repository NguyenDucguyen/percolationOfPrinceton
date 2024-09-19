import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
     private  static final int TOP =0;
     private final boolean [][] opened;
     private final int size;
     private final int bottom;
     private int openSites;
     private final WeightedQuickUnionUF qf;


    public Percolation(int n){
         if(n<=0) throw new IllegalArgumentException();
         size = n;
         bottom = size * size +1;
         opened = new boolean[size][size];
         qf = new WeightedQuickUnionUF(size*size+2);
         openSites = 0;
    }
    private void checkException(int row , int col){
        if(col<=0 || col>size || row<=0 || row>size) throw new IllegalArgumentException();
    }

    public void open(int row, int col){
       checkException(row,col);  // ham check doi so hop phap
       opened[row-1][col-1] = true;
       ++openSites;

       if(row ==1){
           qf.union(getQFIndex(row,col),TOP);
       }
       if(row == size){
           qf.union(getQFIndex(row,col),bottom);
       }
       // o giua
       if(row >1  && isOpen(row-1,col)){
           qf.union(getQFIndex(row,col),getQFIndex(row-1,col));
       }
       if(row<size && isOpen(row+1,col)){
           qf.union(getQFIndex(row,col),getQFIndex(row+1,col));
       }
       if(col >1  && isOpen(row,col-1)){
           qf.union(getQFIndex(row,col),getQFIndex(row,col-1));
       }
       if(col < size && isOpen(row,col+1)){
           qf.union(getQFIndex(row,col),getQFIndex(row,col+1));
       }


    }

    private int getQFIndex(int row, int col){
        return size * (row-1) + col;
    }

    // cái này để check xem ô có mở ko , mở thì true
    public boolean isOpen(int row, int col){
             checkException(row,col);
             return opened[row-1][col-1];
    }


    public boolean isFull(int row, int col){
           if(row >=1 && row <= size && col >=1 && col <= size){
               return qf.find(TOP) == qf.find(getQFIndex(row,col)); //ham find tra ve id[i] => voi qf.connected(TOP,getQFIndex(row,col));
           }
           else  throw new IllegalArgumentException();
    }


    public int numberOfOpenSites(){
             return openSites;

    }


    public boolean percolates(){
         return   qf.find(TOP ) == qf.find(bottom) ; // nuoc da thoat dc
    }


    public static void main(String[] args){



    }
}
