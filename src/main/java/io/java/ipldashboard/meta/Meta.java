package io.java.ipldashboard.meta;

import java.util.HashMap;
import java.util.Map;

import io.java.ipldashboard.model.Team;

public class Meta {
    
        public static Map<String, Team> teamData = new HashMap<>();

        public static Map<String, Team> getTeamData() {
            return teamData;
        }

        public static void setTeamData(Map<String, Team> teamData) {
            Meta.teamData = teamData;
        }

}
