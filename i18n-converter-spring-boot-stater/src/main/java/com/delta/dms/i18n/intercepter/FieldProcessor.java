package com.delta.dms.i18n.intercepter;

import java.beans.PropertyDescriptor;

interface FieldProcessor {
  void process(PropertyDescriptor ps, Object object) throws Exception;
}
