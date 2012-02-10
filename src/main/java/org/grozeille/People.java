package org.grozeille;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({
    @CompoundIndex(name = "firstname_lastname", def = "{'lastName': 1, 'firstname': 1}")
})
public class People {
	@Id 
	private ObjectId id;
	
	private String firstname;
	
	private String lastname;
	
	private Integer revision;
	
	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

	private List<Address> addresses; 

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
