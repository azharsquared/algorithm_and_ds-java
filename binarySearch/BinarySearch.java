/**
 * Binary Search Implementation with Comprehensive Improvements
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class BinarySearch {
    
    /**
     * Performs binary search on a sorted array to find target element
     * 
     * @param arr sorted array of integers (must be non-decreasing order)
     * @param target the element to search for
     * @return index of target if found, -1 otherwise
     * @throws IllegalArgumentException if array is null
     */
    public static int binarySearch(int[] arr, int target) {
        // Input validation with better exception handling
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        // Handle empty array case
        if (arr.length == 0) {
            return -1;
        }
        
        // Initialize search boundaries
        int left = 0;
        int right = arr.length - 1;
        
        // Main search loop
        // Invariant: if target exists, it's within [left, right]
        while (left <= right) {
            // Calculate middle index (prevents integer overflow)
            int mid = left + (right - left) / 2;
            
            // Three-way comparison
            if (arr[mid] == target) {
                return mid; // Found target
            } else if (arr[mid] < target) {
                // Target is in right half: [mid+1, right]
                left = mid + 1;
            } else {
                // Target is in left half: [left, mid-1]  
                right = mid - 1;
            }
        }
        
        // Target not found
        return -1;
    }
    
    /**
     * Generic version that works with any Comparable type
     */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        if (arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            int comparison = arr[mid].compareTo(target);
            
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Recursive version of binary search
     */
    public static int binarySearchRecursive(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        return binarySearchRecursiveHelper(arr, target, 0, arr.length - 1);
    }
    
    private static int binarySearchRecursiveHelper(int[] arr, int target, int left, int right) {
        // Base case: search space is empty
        if (left > right) {
            return -1;
        }
        
        // Calculate middle point
        int mid = left + (right - left) / 2;
        
        // Three-way comparison
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursiveHelper(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursiveHelper(arr, target, left, mid - 1);
        }
    }
    
    /**
     * Find the first occurrence of target (for arrays with duplicates)
     */
    public static int findFirst(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // Continue searching in left half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Find the last occurrence of target (for arrays with duplicates)
     */
    public static int findLast(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // Continue searching in right half
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    /**
     * Find insertion point for target (where it should be inserted to maintain sorted order)
     */
    public static int findInsertionPoint(int[] arr, int target) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return left; // Insertion point
    }
    
    /**
     * Comprehensive test method
     */
    public static void main(String[] args) {
        System.out.println("=== Binary Search Tests ===");
        
        // Test 1: Normal case
        int[] arr = {2, 4, 6, 8, 10, 12, 14};
        testBinarySearch("Normal case", arr, 10, 4);
        testBinarySearch("Element not found", arr, 5, -1);
        testBinarySearch("First element", arr, 2, 0);
        testBinarySearch("Last element", arr, 14, 6);
        
        // Test 2: Edge cases
        int[] emptyArr = {};
        testBinarySearch("Empty array", emptyArr, 5, -1);
        
        int[] singleFound = {5};
        testBinarySearch("Single element (found)", singleFound, 5, 0);
        
        int[] singleNotFound = {5};
        testBinarySearch("Single element (not found)", singleNotFound, 3, -1);
        
        // Test 3: Two elements
        int[] twoElements = {3, 7};
        testBinarySearch("Two elements (first)", twoElements, 3, 0);
        testBinarySearch("Two elements (second)", twoElements, 7, 1);
        testBinarySearch("Two elements (not found)", twoElements, 5, -1);
        
        // Test 4: Duplicates
        System.out.println("\n=== Duplicate Handling Tests ===");
        int[] withDuplicates = {1, 2, 2, 2, 3, 4, 4, 5};
        System.out.println("Array: " + java.util.Arrays.toString(withDuplicates));
        System.out.println("Regular search for 2: " + binarySearch(withDuplicates, 2));
        System.out.println("First occurrence of 2: " + findFirst(withDuplicates, 2));
        System.out.println("Last occurrence of 2: " + findLast(withDuplicates, 2));
        System.out.println("First occurrence of 4: " + findFirst(withDuplicates, 4));
        System.out.println("Last occurrence of 4: " + findLast(withDuplicates, 4));
        
        // Test 5: Insertion points
        System.out.println("\n=== Insertion Point Tests ===");
        int[] sortedArr = {1, 3, 5, 7, 9};
        System.out.println("Array: " + java.util.Arrays.toString(sortedArr));
        System.out.println("Insertion point for 0: " + findInsertionPoint(sortedArr, 0)); // 0
        System.out.println("Insertion point for 4: " + findInsertionPoint(sortedArr, 4)); // 2
        System.out.println("Insertion point for 10: " + findInsertionPoint(sortedArr, 10)); // 5
        
        // Test 6: Generic version
        System.out.println("\n=== Generic Version Tests ===");
        String[] strings = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("String array search for 'cherry': " + binarySearch(strings, "cherry"));
        System.out.println("String array search for 'grape': " + binarySearch(strings, "grape"));
        
        // Test 7: Exception handling
        System.out.println("\n=== Exception Handling Tests ===");
        try {
            binarySearch(null, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly caught exception: " + e.getMessage());
        }
    }
    
    /**
     * Helper method for testing
     */
    private static void testBinarySearch(String testName, int[] arr, int target, int expected) {
        int result = binarySearch(arr, target);
        String status = (result == expected) ? "✓ PASS" : "✗ FAIL";
        System.out.printf("%-25s: target=%2d, result=%2d, expected=%2d %s%n", 
                         testName, target, result, expected, status);
    }
}