package org.optaconf.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
@Entity(name="optaconf_schedule")
public class Schedule extends AbstractPersistable implements Solution<HardSoftScore>
{
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<Day> dayList = new ArrayList<Day>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<Timeslot> timeslotList = new ArrayList<Timeslot>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<Room> roomList = new ArrayList<Room>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<UnavailableTimeslotRoomPenalty> unavailableTimeslotRoomPenaltyList = new ArrayList<UnavailableTimeslotRoomPenalty>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<Track> trackList = new ArrayList<Track>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<Speaker> speakerList = new ArrayList<Speaker>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<Talk> talkList = new ArrayList<Talk>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<SpeakingRelation> speakingRelationList = new ArrayList<SpeakingRelation>();
   @OneToMany(mappedBy="schedule", cascade=CascadeType.ALL)
   private List<TalkExclusion> talkExclusionList = new ArrayList<TalkExclusion>();

   private HardSoftScore score;

   public Schedule()
   {}

   public Schedule(String id)
   {
      super(id);
   }

   public List<Day> getDayList()
   {
      return dayList;
   }

   public void setDayList(List<Day> dayList)
   {
      this.dayList = dayList;
   }

   @ValueRangeProvider(id = "timeslotRange")
   public List<Timeslot> getTimeslotList()
   {
      return timeslotList;
   }

   public void setTimeslotList(List<Timeslot> timeslotList)
   {
      this.timeslotList = timeslotList;
   }

   @ValueRangeProvider(id = "roomRange")
   public List<Room> getRoomList()
   {
      return roomList;
   }

   public void setRoomList(List<Room> roomList)
   {
      this.roomList = roomList;
   }

   public List<UnavailableTimeslotRoomPenalty> getUnavailableTimeslotRoomPenaltyList()
   {
      return unavailableTimeslotRoomPenaltyList;
   }

   public void setUnavailableTimeslotRoomPenaltyList(
            List<UnavailableTimeslotRoomPenalty> unavailableTimeslotRoomPenaltyList)
   {
      this.unavailableTimeslotRoomPenaltyList = unavailableTimeslotRoomPenaltyList;
   }

   public List<Track> getTrackList()
   {
      return trackList;
   }

   public void setTrackList(List<Track> trackList)
   {
      this.trackList = trackList;
   }

   public List<Speaker> getSpeakerList()
   {
      return speakerList;
   }

   public void setSpeakerList(List<Speaker> speakerList)
   {
      this.speakerList = speakerList;
   }

   @PlanningEntityCollectionProperty
   public List<Talk> getTalkList()
   {
      return talkList;
   }

   public void setTalkList(List<Talk> talkList)
   {
      this.talkList = talkList;
   }

   public List<TalkExclusion> getTalkExclusionList()
   {
      return talkExclusionList;
   }

   public List<SpeakingRelation> getSpeakingRelationList()
   {
      return speakingRelationList;
   }

   public void setSpeakingRelationList(List<SpeakingRelation> speakingRelationList)
   {
      this.speakingRelationList = speakingRelationList;
   }

   public void setTalkExclusionList(List<TalkExclusion> talkExclusionList)
   {
      this.talkExclusionList = talkExclusionList;
   }

   public HardSoftScore getScore()
   {
      return score;
   }

   public void setScore(HardSoftScore score)
   {
      this.score = score;
   }

   @Override
   public Collection<?> getProblemFacts()
   {
      List<Object> facts = new ArrayList<Object>();
      facts.addAll(dayList);
      facts.addAll(timeslotList);
      facts.addAll(roomList);
      facts.addAll(unavailableTimeslotRoomPenaltyList);
      facts.addAll(trackList);
      facts.addAll(speakerList);
      facts.addAll(speakingRelationList);
      facts.addAll(talkList);
      facts.addAll(talkExclusionList);
      // Do not add the planning entity's (processList) because that will be done automatically
      return facts;
   }

}
