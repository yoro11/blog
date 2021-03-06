package com.web.blog.util;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.blog.entity.Jokes;
import com.web.blog.entity.Notes;

public class PaginationJokes {

	private static final Logger logger = LogManager.getLogger(PaginationJokes.class);

		//how many pages display on one page
		static int displayPages = 5;
		
		public static List<Jokes> displayPages(List<Jokes> jokes, String pages) {
			
			int page = pageCantbeNegative(Integer.parseInt(pages));

			int start = page*displayPages-displayPages;
			int end = start + displayPages;
			
			start = ValueCantBeNegative(start,jokes.size());
			end   = ValueCantBeNegative(end,jokes.size());
			
			return jokes.subList(start, end);
		}

		public static int pageCantbeNegative(int result) {
			return result<=1?1:result;
		}

		public static int ValueCantBeNegative(int value, int size) {
			
			value= value>=size?size:value; //page can't be more than size of array
			
			value = value<=0?0:value; // value can't be negative;
			
			return value;
		}

		public static List<Integer> createPaginationList(List<Jokes> notes) {
			
			int pages = notes.size()/displayPages+1;
			
			List<Integer> pagination = IntStream.rangeClosed(1, pages).boxed().collect(Collectors.toList());;
			
			return pagination;
		}

		public static String isItNextButton(String page) {
			return page.contains("next")? String.valueOf(Integer.parseInt(page.substring(4))+1):page;
		}

		public static Object getMaxValue(List<Integer> pagination) {
			return pagination
					.stream()
					.mapToInt(v -> v)
					.max().orElseThrow(NoSuchElementException::new);
			
		}
		
}
