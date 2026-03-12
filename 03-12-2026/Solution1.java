// You are given a set of releases of a software and you are asked to find the most
// recent release. There may be multiple checkins of the software in a given branch. 
// Each branch may also have sub branches. For example release 3-5-4 refers to the 
// fourth checkin in the fifth sub branch of the third main branch. 
// This hierarchy can go upto any number of levels. 

// If a level is missing it is considered as level 0 in that hierarchy. 
// For example 3-5-7 is  same as 3-5-7-0 or even same as 3-5-7-0-0. 
// The higher numbers denote more recent releases. 

// For example 3-5-7-1 is more recent than 3-5-7 but less recent than 3-6.

// Input Format:
// -------------
// A single line space separated strings, list of releases 

// Output Format:
// --------------
// Print the latest release of the software.


// Sample Input-1:
// ---------------
// 1-2 1-2-3-0-0 1-2-3

// Sample Output-1:
// ----------------
// 1-2-3

// Sample Input-2:
// ---------------
// 3-5-4 3-5-7 3-5-7-1 3-5-7-0-0 3-6

// Sample Output-2:
// ----------------
// 3-6

import java.util.*;
class Solution1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<inp.length;i++){
            String[] temp = inp[i].split("-");
            List<Integer> list = new ArrayList<>();
            for(String c:temp) list.add(Integer.parseInt(c));
            graph.add(list);
        }
        List<Integer> latest = graph.get(0);
        for(int i=1;i<graph.size();i++){
            if(check(latest,graph.get(i))) latest=graph.get(i);
        }
        for(int i=0;i<latest.size();i++){
            if(i==latest.size()-1){
                if(latest.get(i)!=0) System.out.print(latest.get(i));
            }else{
                if(latest.get(i)!=0) System.out.print(latest.get(i)+"-");
            }
        }
    }
    public static boolean check(List<Integer> first,List<Integer> second){
        int minlen=Math.min(first.size(),second.size());
        for(int i=0;i<minlen;i++){
            if(first.get(i)>second.get(i)) return false;
            else if(first.get(i)<second.get(i)) return true;
        }
        if(first.size()>=second.size()){
            if(first.get(second.size())==0) return true;
            
        }else{
            if(second.get(first.size())!=0) return true;
        }
        return false;
    }
}