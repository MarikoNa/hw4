import java.util.*;
import java.io.*;

public class PageSearch {
    static String[] links;//グラフ //参照している数字を","で区切ってStringで保存
    static boolean[] visited;//参照されていたpage
    static String[] pages;//pageの名前
    static int n;//page数
    public static void main(String[] args) {
	n = 1483277;
	links = new String[n];
	pages = new String[n];
	visited = new boolean[n];
	Scanner sc = new Scanner(System.in);
	inputPages();
	inputLinks();
	findvisitedPage();
	printNotVistedPage();
	printNolinksPage();
    }
    static void inputPages() {//page.txtをpagesに保存
	try(BufferedReader br = new BufferedReader(new FileReader("pages.txt"))) {
	    String line;
	    while((line = br.readLine()) != null) {
		String[] s = line.split("	");
		int i = Integer.parseInt(s[0]);
		pages[i] = s[1];
	    }			
	} catch (IOException e){
            e.printStackTrace();
        }
	System.out.println("page,done");
    }
    static void inputLinks() {//rink.txtの内容をrinksに保存
	for(int j=0;j<links.length;j++) {
	    links[j] = "";
	}
	try(BufferedReader br = new BufferedReader(new FileReader("links.txt"))) {
	    String line;
	    int i = 0;
	    while((line = br.readLine()) != null) {
		String[] s = line.split("	");
		int from = Integer.parseInt(s[0]);
		links[from] += s[1]+","; //fromが参照しているpageをまとめた文字列に
	    }
	}catch (IOException e){
            e.printStackTrace();
        }
	System.out.println("link,done");
    }
    static void findvisitedPage() {//何かに参照されているpageをvisitedに
	for(int i=0;i<links.length;i++) {
	    if(links[i] != "") {
		String[] s = links[i].split(",");
		for(int k=0;k<s.length;k++) {
		    int to = Integer.parseInt(s[k]);　//iが参照しているpage
		    visited[to] = true;
		}
	    }
	}
    }
    static void printNotVistedPage() {//参照されていないpageを書き出す
	System.out.println("--参照されていないpage--");
	int count = 0;
	for(int i=0;i<visited.length;i++) {
	    if(visited[i] == false)  {
		count++;
		System.out.println(pages[i]);
	    }
	}
	System.out.println("page="+count);//参照されていないpage数
    }
    static void printNolinksPage() {　//参照していない且つ参照されてないpageを書き出す
	System.out.println("---どこも参照していないpage---");
	int count = 0;
	for(int i=0;i<visited.length;i++) {
	    if(visited[i]==false && links[i]=="") {
		count++;
		System.out.println(pages[i]);
	    }
	}
	System.out.println("page="+count); //page数
    }
}
