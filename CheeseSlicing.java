/**
* <h1>Cheese Slicing</h1>
* The CheeseSlicing program implements an application that
* recursively calculates the maximum total surface area of all "good"
* slices split from an initial block of cheese. The result
* is displayed to the standard output. The block of cheese can be cut
* into arbitrarily many pieces, but each split must split the parent
* block into 2 slices.
*
* The rules for slicing include:
** Each cut must divide one block of cheese into two smaller blocks of
*   cheese.
** Each cut must be parallel to one of the faces of the piece you are
*   cutting.
** Each of the two smaller blocks must have integer dimensions.
*
* A good slice is a slice where no dimension is less than S.
*
* @author  Louis Coleman
* @version 1.0
* @since   2017-05-06
*/
public class CheeseSlicing {

    public static void main (String args[]){
        System.out.println("Total surface area of cut good slices = " + totalArea(1,3,3,2));
        System.out.println("Total surface area of cut good slices = " + totalArea(5,3,5,3));
        System.out.println("Total surface area of cut good slices = " + totalArea(5,5,5,2));
        System.out.println("Total surface area of cut good slices = " + totalArea(49,92,20,3));
    }

    /**
    * Recursive function that calculates the total surface area of all
    * good slices that can be carved from an initial block of cheese.
    * The smallest dimension of the slice is its thickness. The surface
    * area is a product of the other two dimensions of the slice.
    *
    * @param A x-dimension of the slice of cheese
    * @param B y-dimension of the slice of cheese
    * @param C z-dimension of the slice of cheese
    * @param S minimum thickness threshold for a good cheese slice
    *
    * @return totalSurfaceArea returns the total surface area of all good slices
    */
    public static int totalArea(int A, int B, int C, int S) {

        int totalSurfaceArea = 0;

        // If bad slice then don't add to total surface area
        int thickness = findThickness(A, B, C);
        if ( thickness < S ) return 0;

        // If slice can be split further then return surface area of subsequent good slices
        if ( A >= S * 2) return totalArea(A - S, B, C, S) + totalArea(S, B, C, S);
        if ( B >= S * 2) return totalArea(A, B - S, C, S) + totalArea(A, S, C, S);
        if ( C >= S * 2) return totalArea(A, B, C - S, S) + totalArea(A, B, S, S);

        // If slice can't be split, return total surface area
        totalSurfaceArea = calculateSliceSurfaceArea(A, B, C, thickness);
        return totalSurfaceArea;
    }

    /**
    * Calculates the surface area of the current slice. The surface area
    * is the product of the two dimensions that aren't its thickness.
    *
    * @param A x-dimension of the slice of cheese
    * @param B y-dimension of the slice of cheese
    * @param C z-dimension of the slice of cheese
    * @param thickness thickness of the slice of cheese
    *
    * @return surfaceArea returns the total surface area of all good slices
    */
    public static int calculateSliceSurfaceArea(int A, int B, int C, int thickness){

        int surfaceArea = 0;

        if ( A == thickness ) surfaceArea = B * C;
        if ( B == thickness ) surfaceArea = A * C;
        if ( C == thickness ) surfaceArea = A * B;

        return surfaceArea;
    }

    /**
    * Determines the thickness of the current slice of cheese. The
    * thickness is the smallest dimension of the slice.
    *
    * @param A x-dimension of the slice of cheese
    * @param B y-dimension of the slice of cheese
    * @param C z-dimension of the slice of cheese
    *
    * @return minimum returns the thickness of the slice of cheese
    */
    public static int findThickness(int A, int B, int C) {

        int minimum = A;

        if ( B < minimum ) minimum = B;
        if ( C < minimum ) minimum = C;

        return minimum;
    }
}
