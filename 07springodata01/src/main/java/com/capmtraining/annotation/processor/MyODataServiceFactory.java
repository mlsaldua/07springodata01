package com.capmtraining.annotation.processor;

import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.edm.AnnotationEdmProvider;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.rt.RuntimeDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.capmtraining.util.SpringUtils;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class MyODataServiceFactory extends ODataServiceFactory {
	
	private static final Logger log=LoggerFactory.getLogger(MyODataAnnotationDs.class);

	private String packageToScan;
	
	private ODataService MY_ODATA_SERVICE;
	
	//@Autowired
	//private ApplicationContext appContext;
	
	public MyODataServiceFactory(String packageToScan) {
		this.packageToScan = packageToScan;
	}
	
	@Override
	public ODataService createService(ODataContext ctx) throws ODataException {
		
		if (this.MY_ODATA_SERVICE == null) {
			AnnotationEdmProvider edmProvider = new AnnotationEdmProvider(packageToScan);
			MyODataAnnotationDs dataSource = new MyODataAnnotationDs(packageToScan);
			dataSource.setAppContext(SpringUtils.getApplicationContext());
		    AnnotationValueAccess valueAccess = new AnnotationValueAccess();
		    this.MY_ODATA_SERVICE = RuntimeDelegate.createODataSingleProcessorService(edmProvider,
			        new MyODataProcessor(dataSource, valueAccess));
		    log.debug("MyODataServiceFactory service created");
		}
		return this.MY_ODATA_SERVICE;
	}
}
