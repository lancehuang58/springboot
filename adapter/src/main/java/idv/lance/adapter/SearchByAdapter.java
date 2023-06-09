package idv.lance.adapter;

import idv.lance.search.ISearch;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchByAdapter implements ISearch {
  @Override
  public List<String> execute() {
    return Collections.singletonList("from adapter");
  }
}
