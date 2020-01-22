import java.util.Arrays;

public class Median {
    public static void main(String[] args) {
        int[][] mat = { {1,2,3},
                        {4,5,6},
                        {7,8,9} };

        // 1, 2, 3, 4, (5), 7, 7, 10, 11

        int r = mat.length;
        int c = mat[0].length;
        System.out.println("Median is " + binaryMedian(mat, r, c));
    }


    private static int binaryMedian(int[][] mat, int r, int c) {
        // find min and max:
        // since the matrix is row-wise sorted min can be found on element at [0] index of the rows
        // and max at [c-1] index
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < r; i++) {
            if(mat[i][0] < min) min = mat[i][0];
            if(mat[i][c-1] > max) max = mat[i][c-1];
        }

        // calculate the amount of numbers that should be smaller than the median
        int requiredAmount = (r*c+1)/2;

        while (min < max){
            int mid = (min + max)/2;
            int t_count = 0; // total count of numbers smaller than the mid
            int c_count; // count of numbers smaller than the mid in current row

            // one by one travers through rows (arrays) and count the number of elements smaller than mid
            for (int i = 0; i < r; i++) {

                // to find the number of elements smaller than mid in the current row
                // we are going to find it's "place" in that row

                // to do that we should find it's index
                /* we can use the inbuilt binary search method:
                c_count = Arrays.binarySearch(mat[i], mid);

                or create and use our own:
                */
                c_count = binarySearch(mat[i], mid);


                // if mid hasn't been found and insertion point has been returned
                // we are still able to get the number of smaller elements for this row
                if(c_count < 0){
                    c_count = Math.abs(c_count+1);
                }
                // we need a loop here to handle duplicates
                else {
                    while (c_count < mat[i].length && mat[i][c_count] == mid){
                        c_count+=1;
                    }
                }
                t_count += c_count; // add current count to total count
            }

            // search in the (mid+1 to max) segment if the total count isn't enough
            // or in the (min to max) if total count is greater or equal to the desired amount
            if (t_count < requiredAmount) {
                min = mid+1;
            } else max = mid;
        }
        return min;
    }

    private static int binarySearch(int[] arr, int x){
        int low = 0;
        int high = arr.length-1;

        while (low <= high){
            // this operation is bitwise equivalent of (low + high) / 2 but faster.
            // it is a bitwise right shift that shifts to the right last bit.
            // number after the operator in the expression represents how many bits are to be shifted.
            // every shift means division by 2
            // e.g:  10 = 1010; (10 >>> 1) -> 101; 101 = 5
            int mid = (low + high) >>> 1;

            if(arr[mid] == x) return mid;
            else if(arr[mid] < x) low = mid+1;
            else high = mid-1;
        }

        // following the contract if number isn't found we return (-insertion point - 1).
        // insertion point is the index at which number would be placed in a sorted array.
        // as said in the docs we use this form to ensure that returned value will be 0 iff number is found
        return -low-1;
    }
}



