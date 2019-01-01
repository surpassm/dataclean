package com.liaoin.dataclean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mc
 * version 1.0v
 * date 2019/1/1 11:16
 * description TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "区域")
@Entity(name = "f_region")
public class Region {
    @Id
    @GeneratedValue
    @ApiModelProperty("系统标识")
    private Long id ;

    @ApiModelProperty("名称")
    private String name ;


    @JsonIgnore
    @ApiModelProperty("子级")
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private Set<Region> child = new HashSet<>();

    @ManyToOne
    @JoinColumn
    @ApiModelProperty("父级")
    private Region parent;


}
