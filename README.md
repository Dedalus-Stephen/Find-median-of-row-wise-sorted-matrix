# Find-median-of-row-wise-sorted-matrix

![example](https://sun9-10.userapi.com/c200816/v200816639/45bfc/KnRos_OSmVI.jpg)

### Problem Statement:

Given matrix with **r** rows and **c** columns sorted row wise in non-decreasing order find the **median** of that matrix. 

#### Theses:

1) A median is a number that has (N/2) elements less than it. Where N is the total number of elements.

2) It is guaranteed that (r\*c) is odd, so the median would be present in the matrix.

### Approach:

1)Find the required number of elements that should be smaller than median.

2) Find minimum and maximum elements in the matrix.

3) Based of minimum and maximum calculate the middle element.

  :small_red_triangle: Note: here we just assuming the calculated middle element to be the median. It may or may not be present in matrix.
  
4) Count number of elements smaller using binary search. If count is less than *required* then minimum becomes middle + 1 or if the count is greater than or equal to the *required* maximum becomes middle.

5) Repeat steps 3-4 while (minimum < maximum).

### See full explanation in the code file.

After reading the code solution a question may arise: why aren't we returning the value as soon as we've found such middle element that has the required number of elements smaller than it?

The reason is:

As mentioned above the middle value is just assumed median. There are no guarantees that it is present in the matrix. When we find such a **mid** that satisfies the (number_of_elements_less_than_it == ((number_of_rows * number_of_columns +1 )/2)) we just set it to be the upper bound for the searched value. And then we continue shrinking the searched space by either increasing **min** or decreasing **max**.
By returning **min** in the end we guarantee to return median present in the matrix.


Consider the following matrix:

![example1](https://sun9-42.userapi.com/c855728/v855728499/1da137/6oLvBEQeGxQ.jpg)

*requiredAmount = 5*

Intermediate values of **min**, **max**, **mid** obtained during computation:

1) (**min** = 1, **max** = 11, **mid** = 6) 
As you can see mid (6) has 5 smaller elements (1,3,4,2,5) and so satisfies the condition, **but we can't be sure that it is present in matrix.** And as can be seen it is actually not present in our case.
So we set it to be the upper bound since we know now searched element can't be greater than it. **max = mid**

2) (**min** = 1, **max** = 6, **mid** = 3) 
The number of elements smaller than mid is less than required. Shrink the search space: set lower bound as **min = mid+1.** 

3) (**min** = 4, **max** = 6, **mid** = 5) 
**Mid** here satisfies the condition, **but we can't be sure that it is present in matrix.** Though in the matrix we consider it's obviously present. So set new upper bound: **max = mid**.

4) (**min** = 4, **max** = 5, **mid** = 4) 
The number of elements smaller than mid is less than required. Shrink the search space: set lower bound as **min = mid+1.** 

**min = 5, max = 5** 

Now the condition (min < max) is false.

**return min**
