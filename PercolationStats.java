
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int experimentsCount ; // số thí nghiệm
    private final double [] fractions ;

    public PercolationStats(int n, int t){
        if(n<=0 || t<=0){
            throw new IllegalArgumentException();
        }
        experimentsCount = t;
        fractions = new double [experimentsCount];
        for(int expNum = 0 ;expNum< experimentsCount;expNum++){
            Percolation pr = new Percolation(n);
            int openedSites = 0;
            while(!pr.percolates()){
                int i = StdRandom.uniform(n) + 1;
                int j = StdRandom.uniform(n) + 1;
                if(!pr.isOpen(i,j)){
                    pr.open(i,j);
                    openedSites ++;
                }
            }
            double fraction = (double)openedSites/(n*n);    // giá trị trung bình
            fractions[expNum] = fraction;      //gán các giá trị đấy vào mảng fractions

        }

    }

    // sample mean of percolation threshold
    public double mean(){              // tính trung bình , có thể dùng StdStats.mean();
      //  double sum =0;
       //  for(int i=0;i<experimentsCount;i++){
       //      sum+=fractions[i];
      //   }
      //   return sum/experimentsCount;
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {                               // tính độ lệch chuẩn, có thể dùng StdStats.stddev()
       // double sum =0;
         // for(int i=0;i<experimentsCount;i++){
         //     double mean = mean();
          //    sum+=Math.pow(fractions[i]-mean,2);
        //  }
          //return Math.sqrt(sum/(experimentsCount-1));
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(experimentsCount));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(experimentsCount));
    }

    // test client (see below)
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean          = " + ps.mean());
        StdOut.println("stddev        = " + ps.stddev());
        StdOut.println("95% confidence interval = " + "[" + confidence + "]");
    }



}
