package com.feelcode.tourism.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tourism_group")
@Data
public class Group extends BaseEntity{

    @Column(name = "group_name", length = 100)
    private String groupName;

    @Column(name = "group_address", length = 100)
    private String groupAddress;

    @Column(name = "group_phone")
    private String groupPhone;

    @Column(name = "group_member")
    private String groupMember;

    @Column(columnDefinition="text", name = "group_description")
    private String groupDescription;

    @Column(columnDefinition="text", name = "group_images")
    private String groupImages;

}
