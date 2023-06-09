package idv.lance.controller;

import idv.lance.search.ISearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

  @Autowired
//  @Qualifier("searchByAdapter")
  ISearch search;

//  @GetMapping("search/dao")
//  public List<String> searchDao() {
//    return searchByDao.execute();
//  }

  @GetMapping("search/adapater")
  public List<String> searchAdapter() {
    return search.execute();
  }
}
