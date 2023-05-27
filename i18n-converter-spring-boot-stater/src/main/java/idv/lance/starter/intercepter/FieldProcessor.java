package idv.lance.starter.intercepter;

import java.beans.PropertyDescriptor;

interface FieldProcessor {
  void process(PropertyDescriptor ps, Object object) throws Exception;
}
