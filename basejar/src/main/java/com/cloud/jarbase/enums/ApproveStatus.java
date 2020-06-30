package com.cloud.jarbase.enums;

/**
 * @description
 * @create 2019/7/2
 */
public enum ApproveStatus {

	APPLY(1, "appling", "申请中"),
	APPROVED(2, "approved", "审核通过"),
	REJECT(3, "rejected", "已拒绝"),;

	private int value;
	private String nameEn;
	private String nameCn;

	ApproveStatus(int value, String nameEn, String nameCn) {
		this.value = value;
		this.nameEn = nameEn;
		this.nameCn = nameCn;
	}

	ApproveStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public String getNameEn() {
		return this.nameEn;
	}

	public String getNameCn() {
		return this.nameCn;
	}


}
