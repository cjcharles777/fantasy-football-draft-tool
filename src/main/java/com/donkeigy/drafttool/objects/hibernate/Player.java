 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donkeigy.drafttool.objects.hibernate;


 import org.hibernate.annotations.GenericGenerator;
 import org.hibernate.annotations.LazyCollection;
 import org.hibernate.annotations.LazyCollectionOption;

 import javax.persistence.*;
 import java.io.Serializable;
 import java.util.List;

 /**
  *
  * @author DMDD
  */
 @Entity
 @Table(name = "Players")
 //@JsonIgnoreProperties({"eligible_positions", "selected_position"})
 public class Player implements Serializable
 {

     private Integer id;
     private String player_key;
     private String player_id;
     private Name name;
     private String editorial_player_key;
     private String editorial_team_key;
     private String editorial_team_full_name;
     private String editorial_team_abbr;
     private ByeWeek bye_weeks;
     private String uniform_number;
     private String display_position;
     private PlayerPic headshot;
     private String image_url;
     private String is_undroppable;
     private String position_type;
    // private List<Position> eligible_positions;
     private String has_player_notes;
     private String has_recent_player_notes;
     private String status;
     private String on_disabled_list;
     private List<SeasonStat> seasonStats;
     private List<WeeklyStat> weeklyStats;




     @Id
     @GeneratedValue(generator = "generator")
     @GenericGenerator(name = "generator", strategy = "increment")
     @Column(name = "playerid", nullable=false)
     public Integer getId()
     {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

    @Column(name = "player_key", length=20, nullable=false)
     public String getPlayer_key() {
         return player_key;
     }

     public void setPlayer_key(String player_key) {
         this.player_key = player_key;
     }

     @Column(name = "player_id_yahoo", length=7, nullable=false)
     public String getPlayer_id() {
         return player_id;
     }

     public void setPlayer_id(String player_id) {
         this.player_id = player_id;
     }

     @OneToOne(cascade = CascadeType.ALL)
     @PrimaryKeyJoinColumn
     public Name getName() {
         return name;
     }

     public void setName(Name name) {
         this.name = name;
     }

     @Column(name = "editorial_player_key", length=20, nullable=false)
     public String getEditorial_player_key() {
         return editorial_player_key;
     }

     public void setEditorial_player_key(String editorial_player_key) {
         this.editorial_player_key = editorial_player_key;
     }

     @Column(name = "editorial_team_key", length=20, nullable=false)
     public String getEditorial_team_key() {
         return editorial_team_key;
     }

     public void setEditorial_team_key(String editorial_team_key) {
         this.editorial_team_key = editorial_team_key;
     }

     @Column(name = "editorial_team_full_name", length=50, nullable=false)
     public String getEditorial_team_full_name() {
         return editorial_team_full_name;
     }

     public void setEditorial_team_full_name(String editorial_team_full_name) {
         this.editorial_team_full_name = editorial_team_full_name;
     }

     @Column(name = "editorial_team_abbr", length=10, nullable=false)
     public String getEditorial_team_abbr() {
         return editorial_team_abbr;
     }

     public void setEditorial_team_abbr(String editorial_team_abbr) {
         this.editorial_team_abbr = editorial_team_abbr;
     }

     @OneToOne(cascade = CascadeType.ALL)
     @PrimaryKeyJoinColumn
     public ByeWeek getBye_weeks() {
         return bye_weeks;
     }

     public void setBye_weeks(ByeWeek bye_weeks) {
         this.bye_weeks = bye_weeks;
     }

     @Column(name = "uniform_number", length=2, nullable=true)
     public String getUniform_number() {
         return uniform_number;
     }

     public void setUniform_number(String uniform_number) {
         this.uniform_number = uniform_number;
     }

     @Column(name = "display_position", length=10, nullable=false)
     public String getDisplay_position() {
         return display_position;
     }

     public void setDisplay_position(String display_position) {
         this.display_position = display_position;
     }

     @OneToOne(cascade = CascadeType.ALL)
     @PrimaryKeyJoinColumn
     public PlayerPic getHeadshot() {
         return headshot;
     }

     public void setHeadshot(PlayerPic headshot) {
         this.headshot = headshot;
     }

    @Column(name = "image_url", length=1000, nullable=false)
     public String getImage_url() {
         return image_url;
     }

     public void setImage_url(String image_url) {
         this.image_url = image_url;
     }

     @Column(name = "is_undroppable", length=1, nullable=true)
     public String getIs_undroppable() {
         return is_undroppable;
     }

     public void setIs_undroppable(String is_undroppable) {
         this.is_undroppable = is_undroppable;
     }

     @Column(name = "position_type", length=10, nullable=false)
     public String getPosition_type() {
         return position_type;
     }

     public void setPosition_type(String position_type) {
         this.position_type = position_type;
     }


  /**   @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
     @JoinTable(
             name="PlayerToPosition",
             joinColumns = @JoinColumn( name="playerid"),
             inverseJoinColumns = @JoinColumn( name="position")
     )
      @LazyCollection(LazyCollectionOption.FALSE)
     public List<Position> getEligible_positions() {
         return eligible_positions;
     }

     public void setEligible_positions(List<Position> eligible_positions) {
         this.eligible_positions = eligible_positions;
     }
**/
     @Column(name = "has_player_notes", length=1, nullable=true)
     public String getHas_player_notes() {
         return has_player_notes;
     }

     public void setHas_player_notes(String has_player_notes) {
         this.has_player_notes = has_player_notes;
     }

     @Column(name = "has_recent_player_notes", length=1, nullable=true)
     public String getHas_recent_player_notes() {
         return has_recent_player_notes;
     }

     public void setHas_recent_player_notes(String has_recent_player_notes) {
         this.has_recent_player_notes = has_recent_player_notes;
     }

     @OneToMany(cascade = {CascadeType.ALL} )
     @JoinTable(
             name="PlayerToSeasonStats",
             joinColumns = @JoinColumn( name="playerid"),
             inverseJoinColumns = @JoinColumn( name="season_stat_id")
     )
     @LazyCollection(LazyCollectionOption.FALSE)
     public List<SeasonStat> getSeasonStats()
     {
         return seasonStats;
     }

     public void setSeasonStats(List<SeasonStat> seasonStats)
     {
         this.seasonStats = seasonStats;
     }
         @OneToMany(cascade = {CascadeType.ALL} )
     @JoinTable(
             name="PlayerToWeeklyStats",
             joinColumns = @JoinColumn( name="playerid"),
             inverseJoinColumns = @JoinColumn( name="weekly_stat_id")
     )
     @LazyCollection(LazyCollectionOption.FALSE)
     public List<WeeklyStat> getWeeklyStats()
     {
         return weeklyStats;
     }

     public void setWeeklyStats(List<WeeklyStat> weeklyStats)
     {
         this.weeklyStats = weeklyStats;
     }

     @Column(name = "status", length=35, nullable=true)
     public String getStatus() {
         return status;
     }

     public void setStatus(String status) {
         this.status = status;
     }

     @Column(name = "on_disabled_list", length=1, nullable=true)
     public String getOn_disabled_list() {
         return on_disabled_list;
     }

     public void setOn_disabled_list(String on_disabled_list) {
         this.on_disabled_list = on_disabled_list;
     }



 }
