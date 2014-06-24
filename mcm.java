/* Matrix Chain Multiplication
 * by Steven Wirsz
 * based on: Thomas H. Cormen. (page 375)
 * 
 * This program is based on a simple optimum matrix chain multiplication program
 * available on Wikipedia and expanded by calculating worst-case, second-best, 
 * and second worst-case values and parenthesizations
 */

package mcm;

public class MCM 
{
    public static int[] input={2, 6, 3, 7, 7, 6, 4};
    protected static int m[][];
    protected static int s[][];
    protected static int n;
    final static MCM mc=new MCM();
    
    public static void main (final String[] args) {
	
        System.out.println("Optimal Parenthesization\n");
        System.out.println(mc.matrixChainOrder(0,0));
        mc.printOptimalParens(s,0,n-1);
        
        System.out.println("\n\nAlmost Best Parenthesizations");
        mc.almostBest(true);
        
        System.out.println("\n\nWorst-case Parenthesization\n");
        System.out.println(mc.worstMatrixChainOrder(0,0));
        mc.printOptimalParens(s,0,n-1);
        
        System.out.println("\n\nAlmost Worst Parenthesizations");
        mc.almostBest(false);
    } // end main
    
    private void almostBest(boolean Case) {
        java.util.HashSet hs = new java.util.HashSet();
        int v;
        for (int i=2; i <input.length-1; i++) {
            for (int j=i; j <input.length-1; j++) {
                if (Case) {v=mc.matrixChainOrder(i,j);
                } else {
                    v=mc.worstMatrixChainOrder(i,j);
                }
                if (!hs.contains(v)) {
                    hs.add(v);
                    System.out.println("\n"+v);
                    mc.printOptimalParens(s,0,n-1);
                } // end if
            } // end for
        } // end for        
    }
    
    private void initValues() {
        n=input.length-1;
        m=new int[n][n];
        s=new int[n][n];
        for(int i=0;i<n;i++){ // initialize all values to zero
            m[i]=new int[n];
            m[i][i]=0;
            s[i]=new int[n];
        } // end for
    }
    
    public int matrixChainOrder(final int srow, final int scol) {
        int secondbest=0;
        int secondbestpos=0;
        mc.initValues();
        for(int row=1;row<n;row++) {
            for(int i=0;i<n-row;i++) { // # of possible values per row
                final int col=i+row;
                for(int k=i;k<col;k++) {
                    final int eval=m[i][k]+m[k+1][col]+input[i]*input[k+1]*input[col+1];
                    //if (row > 1) {
                    //    System.out.format(" (alt:%d row:%d col:%d sep:%d)\n",eval,row,col,k-i+1); // show all alternatives
                    //}
                    if (k==i) { // first element
                        m[i][col]=eval;
                        s[i][col]=k;                                            
                        secondbest = eval;
                        secondbestpos = k;
                    } else if (k==i+1) { // second element
                        if (m[i][col]>eval) {
                            m[i][col]=eval;
                            s[i][col]=k;
                        } else {
                            secondbest = eval;
                            secondbestpos = k;
                        }
                    } else { // 3rd and subsequent elements      
                        if(eval<m[i][col]){ 
                            secondbest = m[i][col];
                            secondbestpos = s[i][col];
                            m[i][col]=eval;
                            s[i][col]=k;
                        } else if (eval<secondbest) {
                            secondbest = eval;
                            secondbestpos = k;
                        }
                    } // end else
                }
                if (srow==row && scol==col) {
                    m[i][col]=secondbest;
                    s[i][col]=secondbestpos;
       		} // end if
                //   System.out.format("%d 2nd: %d row:%d col:%d\n",m[i][col],secondbest,row,col); // debugging
                if (row==input.length-2) {
                    return m[i][col];
                } // end if
            } // end for
        } // end for
        return 0;
    }
    public int worstMatrixChainOrder(final int srow, final int scol) {
        int secondworst=0;
        int secondworstpos=0;
        mc.initValues();
        for(int row=1;row<n;row++) {
            for(int i=0;i<n-row;i++) { // # of possible values per row
                int col=i+row;
                for(int k=i;k<col;k++) {
                    int eval=m[i][k]+m[k+1][col]+input[i]*input[k+1]*input[col+1];
                    //if (row > 1) {
                    //    System.out.format(" (alt:%d row:%d col:%d sep:%d)\n",eval,row,col,k-i+1); // show all alternatives
                    //}
                    if (k==i) { // first element
                        m[i][col]=eval;
                        s[i][col]=k;                                            
                        secondworst = eval;
                        secondworstpos = k;
                    } else if (k==i+1) { // second element
                        if (m[i][col]<eval) {
                            m[i][col]=eval;
                            s[i][col]=k;
                        } else {
                            secondworst = eval;
                            secondworstpos = k;
                        }
                    } else { // 3rd and subsequent elements      
                        if(eval>m[i][col]){ 
                            secondworst = m[i][col];
                            secondworstpos = s[i][col];
                            m[i][col]=eval;
                            s[i][col]=k;
                        } else if (eval>secondworst) {
                            secondworst = eval;
                            secondworstpos = k;
                        }
                    } // end else
                }
                if (srow==row && scol==col) {
                    m[i][col]=secondworst;
                    s[i][col]=secondworstpos;
       		} // end if                       
                //   System.out.format("%d 2nd: %d row:%d col:%d\n",m[i][col],secondworst,row,col); // debugging
                if (row==input.length-2) {
                    return m[i][col];
                } // end if
            } // end for
        } // end for
        return 0;
    }
    
    void printOptimalParens(final int[][]s, final int i, final int col) {
	if(i==col){
            System.out.print((i+1));
        } else {
            System.out.print("(");
            printOptimalParens(s,i,s[i][col]);
            printOptimalParens(s,s[i][col]+1,col);
            System.out.print(")");
	} // end if
    }
} // end mcm
