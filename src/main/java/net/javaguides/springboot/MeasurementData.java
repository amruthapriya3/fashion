package net.javaguides.springboot.mes;

import javax.persistence.*;


	@Table(name = "measurements")
	@Entity
	public class MeasurementData {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Double bustSize;
	    private Double waistSize;
	    private Double hipsSize;
	    private Double inseamLength;
	    private String name;
	    private String phoneNumber;
	    private String email;
	    private String address;
	    private String suggestion;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Double getBustSize() {
			return bustSize;
		}
		public void setBustSize(Double bustSize) {
			this.bustSize = bustSize;
		}
		public Double getWaistSize() {
			return waistSize;
		}
		public void setWaistSize(Double waistSize) {
			this.waistSize = waistSize;
		}
		public Double getHipsSize() {
			return hipsSize;
		}
		public void setHipsSize(Double hipsSize) {
			this.hipsSize = hipsSize;
		}
		public Double getInseamLength() {
			return inseamLength;
		}
		public void setInseamLength(Double inseamLength) {
			this.inseamLength = inseamLength;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSuggestion() {
			return suggestion;
		}
		public void setSuggestion(String suggestion) {
			this.suggestion = suggestion;
		}

	    // Getters and setters
	}


