/**
 * 
 */
package com.quantifiedCare.core.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author user
 *
 */
@Entity
@Table(name = "theme")
public class Theme extends AbstractBaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6086625975464209511L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "org_logo", nullable = true, length = 50)
	private String orgLogo;
	
	@Column(name = "add_tagline", nullable = true, length = 50)
	private String addTagline;
	
	@Column(name = "set_topheader", nullable = true, length = 50)
	private String setTopHeader;
	
	@Column(name = "set_splashcolour", nullable = true, length = 50)
	private String setSplashColour;
	
	@Column(name = "set_heading_textcolor", nullable = true, length = 50)
	private String setHeadingTextColor;
	
	@Column(name = "set_paragraphcolor", nullable = true, length = 50)
	private String setParagraphColor;
	
	@OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL, CascadeType.REMOVE})
	private Organizations organizations;
	
	public Organizations getOrganizations() {
		return organizations;
	}
	public void setOrganizations(Organizations organizations) {
		this.organizations = organizations;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public String getOrgLogo() {
		return orgLogo;
	}
	public void setOrgLogo(String orgLogo) {
		this.orgLogo = orgLogo;
	}
	public String getAddTagline() {
		return addTagline;
	}
	public void setAddTagline(String addTagline) {
		this.addTagline = addTagline;
	}
	public String getSetTopHeader() {
		return setTopHeader;
	}
	public void setSetTopHeader(String setTopHeader) {
		this.setTopHeader = setTopHeader;
	}
	public String getSetSplashColour() {
		return setSplashColour;
	}
	public void setSetSplashColour(String setSplashColour) {
		this.setSplashColour = setSplashColour;
	}
	public String getSetHeadingTextColor() {
		return setHeadingTextColor;
	}
	public void setSetHeadingTextColor(String setHeadingTextColor) {
		this.setHeadingTextColor = setHeadingTextColor;
	}
	public String getSetParagraphColor() {
		return setParagraphColor;
	}
	public void setSetParagraphColor(String setParagraphColor) {
		this.setParagraphColor = setParagraphColor;
	}

	
	
}
