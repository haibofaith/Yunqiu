package com.kball.function.CloudBall.bean;

import java.io.Serializable;

/**
 * Created by user on 2017/3/23.
 *
 * team_id	String	球队ID
 * cloud_id	String	云五ID
 * attack_gross	int	进攻值
 * attack_gains	int	进攻变化值
 * defensive_gross	int	防守值
 * defensive_gains	int	防守变化值
 * physical_gross	int	体能值
 * physical_gains	int	体能变化值
 * technology_gross	int	技术值
 * technology_gains	int	技术变化值
 * aggressive_gross	int	侵略性
 * aggressive_gains	int	侵略性变化值
 * mean_power	int	综合评分
 * gains	int	综合评分变化值
 */

public class GetTeamPowerBean implements Serializable {
    private String team_id;
    private String cloud_id;
    private String attack_gross;
    private String attack_gains;
    private String defensive_gross;
    private String defensive_gains;
    private String physical_gross;
    private String physical_gains;
    private String technology_gross;
    private String technology_gains;
    private String aggressive_gross;
    private String aggressive_gains;
    private String mean_power;
    private String gains;
    private String count_time;

    public String getCount_time() {
        return count_time;
    }

    public void setCount_time(String count_time) {
        this.count_time = count_time;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getCloud_id() {
        return cloud_id;
    }

    public void setCloud_id(String cloud_id) {
        this.cloud_id = cloud_id;
    }

    public String getAttack_gross() {
        return attack_gross;
    }

    public void setAttack_gross(String attack_gross) {
        this.attack_gross = attack_gross;
    }

    public String getAttack_gains() {
        return attack_gains;
    }

    public void setAttack_gains(String attack_gains) {
        this.attack_gains = attack_gains;
    }

    public String getDefensive_gross() {
        return defensive_gross;
    }

    public void setDefensive_gross(String defensive_gross) {
        this.defensive_gross = defensive_gross;
    }

    public String getDefensive_gains() {
        return defensive_gains;
    }

    public void setDefensive_gains(String defensive_gains) {
        this.defensive_gains = defensive_gains;
    }

    public String getPhysical_gross() {
        return physical_gross;
    }

    public void setPhysical_gross(String physical_gross) {
        this.physical_gross = physical_gross;
    }

    public String getPhysical_gains() {
        return physical_gains;
    }

    public void setPhysical_gains(String physical_gains) {
        this.physical_gains = physical_gains;
    }

    public String getTechnology_gross() {
        return technology_gross;
    }

    public void setTechnology_gross(String technology_gross) {
        this.technology_gross = technology_gross;
    }

    public String getTechnology_gains() {
        return technology_gains;
    }

    public void setTechnology_gains(String technology_gains) {
        this.technology_gains = technology_gains;
    }

    public String getAggressive_gross() {
        return aggressive_gross;
    }

    public void setAggressive_gross(String aggressive_gross) {
        this.aggressive_gross = aggressive_gross;
    }

    public String getAggressive_gains() {
        return aggressive_gains;
    }

    public void setAggressive_gains(String aggressive_gains) {
        this.aggressive_gains = aggressive_gains;
    }

    public String getMean_power() {
        return mean_power;
    }

    public void setMean_power(String mean_power) {
        this.mean_power = mean_power;
    }

    public String getGains() {
        return gains;
    }

    public void setGains(String gains) {
        this.gains = gains;
    }
}
