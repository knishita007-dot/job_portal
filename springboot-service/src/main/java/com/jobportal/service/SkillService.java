package com.jobportal.service;
import com.jobportal.entity.Skill;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.SkillRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SkillService {
    private final SkillRepository skillRepository;
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Skill", id));
    }
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new ResourceNotFoundException("Skill", id);
        }
        skillRepository.deleteById(id);
    }
    public SkillService(final SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }
}
