package org.grozeille.listeners;

import java.util.Calendar;

import org.grozeille.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component("beforeSaveListener")
public class BeforeSaveListener extends AbstractMongoEventListener<People> {

	private MongoTemplate mongoTemplate;

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void onBeforeSave(People source, DBObject dbo) {
		if (source.getRevision() == null) {
			source.setRevision(0);
		} else {
			source.setRevision(source.getRevision() + 1);
		}
		
		dbo.put("revision", source.getRevision());
		
		super.onBeforeSave(source, dbo);
	}
	
	@Override
	public void onAfterSave(People source, DBObject dbo) {
		super.onAfterSave(source, dbo);

		DBObject auditDbo = new BasicDBObject();
		auditDbo.put("time", Calendar.getInstance().getTime());
		auditDbo.put("data", dbo);		
		
		this.mongoTemplate.getCollection("people_audit").save(auditDbo);
	}
}
