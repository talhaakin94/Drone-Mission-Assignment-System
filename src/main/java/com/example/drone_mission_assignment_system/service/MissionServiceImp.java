package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dao.MissionRepository;
import com.example.drone_mission_assignment_system.dto.MissionRequest;
import com.example.drone_mission_assignment_system.entity.DroneStatus;
import com.example.drone_mission_assignment_system.entity.Mission;
import com.example.drone_mission_assignment_system.entity.MissionStatus;
import com.example.drone_mission_assignment_system.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MissionServiceImp implements MissionService {
    private final MissionRepository missionRepository;
    public MissionServiceImp(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }
    @Override
    public Mission create(MissionRequest missionRequest) {
        Mission mission = new Mission();
        if(missionRequest.drone().getDroneStatus() == DroneStatus.MAINTENANCE) {
            throw new SystemException("A drone in maintenance cannot be assigned to a mission.", HttpStatus.BAD_REQUEST);
        }
        if(missionRequest.drone().getDroneStatus() == DroneStatus.ACTIVE) {
            throw new SystemException("An active drone cannot be assigned to another mission.", HttpStatus.BAD_REQUEST);
        }
        if(missionRequest.missionStatus() != MissionStatus.ACTIVE) {
            throw new SystemException("A cancelled or finished mission cannot be created.", HttpStatus.BAD_REQUEST);
        }
        mission.setName(missionRequest.name());
        mission.setDescription(missionRequest.description());
        mission.setMissionStatus(missionRequest.missionStatus());
        mission.setMissionPriority(missionRequest.missionPriority());
        mission.setLongitude(missionRequest.longitude());
        mission.setLatitude(missionRequest.latitude());
        mission.setAltitude(missionRequest.altitude());
        mission.setOperator(missionRequest.operator());
        mission.setDrone(missionRequest.drone());
        mission.getDrone().setDroneStatus(DroneStatus.ACTIVE);
        mission.getDrone().getMissions().add(mission);
        mission.getOperator().getMissions().add(mission);
        return missionRepository.save(mission);
    }
    @Override
    public List<Mission> getAll() {
        return missionRepository.findAll();
    }
    @Override
    public Mission getById(Long id) {
        return missionRepository.findById(id).orElseThrow(() -> new SystemException("Mission not found.", HttpStatus.NOT_FOUND));
    }
    @Override
    public Mission update(Long id, MissionRequest missionRequest) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new SystemException("Mission to update not found.", HttpStatus.NOT_FOUND));
        if(mission.getMissionStatus() == MissionStatus.CANCELLED && missionRequest.missionStatus() == MissionStatus.ACTIVE) {
            throw new SystemException("A cancelled mission cannot be activated.", HttpStatus.BAD_REQUEST);
        }
        if(mission.getMissionStatus() == MissionStatus.FINISHED && missionRequest.missionStatus() != MissionStatus.FINISHED) {
            throw new SystemException("A finished mission cannot be activated or cancelled.", HttpStatus.BAD_REQUEST);
        }
        if(mission.getDrone().getDroneStatus() == DroneStatus.MAINTENANCE && missionRequest.missionStatus() == MissionStatus.ACTIVE) {
            throw new SystemException("A drone in maintenance cannot be activated.", HttpStatus.BAD_REQUEST);
        }
        mission.setName(missionRequest.name());
        mission.setDescription(missionRequest.description());
        mission.setAltitude(missionRequest.altitude());
        mission.setLatitude(missionRequest.latitude());
        mission.setLongitude(missionRequest.longitude());
        mission.setMissionPriority(missionRequest.missionPriority());
        mission.setMissionStatus(missionRequest.missionStatus());
        mission.setDrone(missionRequest.drone());
        mission.setOperator(missionRequest.operator());
        return missionRepository.save(mission);
    }
    @Override
    public Mission remove(Long id) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new SystemException("Mission to remove not found.", HttpStatus.NOT_FOUND));
        mission.getDrone().getMissions().remove(mission);
        mission.getOperator().getMissions().remove(mission);
        missionRepository.delete(mission);
        return mission;
    }
}
