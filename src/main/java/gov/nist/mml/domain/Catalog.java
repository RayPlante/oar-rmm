/**
 * This software was developed at the National Institute of Standards and Technology by employees of
 * the Federal Government in the course of their official duties. Pursuant to title 17 Section 105
 * of the United States Code this software is not subject to copyright protection and is in the
 * public domain. This is an experimental system. NIST assumes no responsibility whatsoever for its
 * use by other parties, and makes no guarantees, expressed or implied, about its quality,
 * reliability, or any other characteristic. We would appreciate acknowledgement if the software is
 * used. This software can be redistributed and/or modified freely provided that any derivative
 * works bear some notice that they are derived from it, and any modified versions bear some notice
 * that they have been modified.
 * @author: Deoyani Nandrekar-Heinis
 */
package gov.nist.mml.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author dsn1
 *
 */

public class catalog {
	@Id private String id;
	@JsonProperty("@context")  @Field("@context")
	private @TextIndexed String context;
	@JsonProperty("@id") @Field("@Id")
	private @TextIndexed String catalogId;
	@JsonProperty("@type") @Field("@type")
	private @TextIndexed String type;
	private @TextIndexed String conformsTo;
	private @TextIndexed String describedBy;
	@JsonProperty("dataset") @Field("dataset")
	//@DBRef
	private @TextIndexed Record[] records;
	
	public String getContext() {
		return context;
	}

	public void setcontext(String context) {
		this.context = context;
	}
	
	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getConformsTo() {
		return conformsTo;
	}

	public void setConformsTo(String conformsTo) {
		this.conformsTo = conformsTo;
	}
	public String getDescribedBy() {
		return describedBy;
	}

	public void setDescribedBy(String describedBy) {
		this.describedBy = describedBy;
	}
	
	public void setRecords(Record[] records)
	{
		this.records = records;
	}
	public Record[] getRecords(){
		return this.records;
	}
}
