package com.evaluation.demo.service;

import com.evaluation.demo.customException.BlankFieldException;
import com.evaluation.demo.dto.HackathonCategoryDto;
import com.evaluation.demo.dto.IdeaDTO;
import com.evaluation.demo.dto.LikeDTO;
import com.evaluation.demo.dto.MemberDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.entity.Idea;
import com.evaluation.demo.entity.Likes;
import com.evaluation.demo.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder.In;

import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaServiceImplementation implements IdeaService {
    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private HackathonService hackathonService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LikeService likeService;

    @Override
    public HackathonCategoryDto getAllIdeasPerHackathon(int hackathonID, int memberID) {
        HackathonCategoryDto hackathonCategoryDTO = new HackathonCategoryDto();
        hackathonCategoryDTO.setHackathonName(hackathonService.getHackathonName(hackathonID));

        List<Likes> likesList = likeService.getAllLikesOfMember(memberID);
        List<Integer> likedIdeaIDs = new ArrayList<>();

        likesList.stream().forEach((like) -> {
            likedIdeaIDs.add(like.getIdeaID().getIdeaID());
        });

        List<IdeaDTO> ideaDTOList = new ArrayList<>();
        List<Idea> ideaList = ideaRepository.findAllByHackathonID_HackathonID(hackathonID);
        List<LikeDTO> likeDTOList = new ArrayList<>();

        ideaList.stream().forEach((idea) -> {
            //ideaDTOList.add(IdeaToIdeaDTO(idea));
            IdeaDTO ideaDTO = IdeaToIdeaDTO(idea);
            if (likedIdeaIDs.contains(idea.getIdeaID())) {
                int pos=likedIdeaIDs.indexOf(idea.getIdeaID());
                ideaDTO.setLiked(true);
                LikeDTO likeDTO=new LikeDTO();
                likeDTO.setLikeID(likesList.get(pos).getLikeID());
                ideaDTO.setLikeDTO(likeDTO);
            }
            else {
                ideaDTO.setLiked(false);
            }
            ideaDTOList.add(ideaDTO);
        });

        hackathonCategoryDTO.setIdeaDTOList(ideaDTOList);
        return hackathonCategoryDTO;
    }

    @Override
    public Idea getIdeaByIdeaID(int ideaID) {
        return ideaRepository.getOne(ideaID);
    }

    @Override
    public ResponseDTO addIdea(IdeaDTO ideaDTO, int hackathonID) throws BlankFieldException {
        System.out.println("Entry into service impl");
        if (ideaDTO.getIdeaDetails().isEmpty() || ideaDTO.getIdeaSummary().isEmpty()) {
            throw new BlankFieldException("Details and Summary can't be blank!");
        }
        Idea idea = IdeaDTOToIdea(ideaDTO);
        idea.setHackathonID(hackathonService.getHackathonByID(hackathonID));
        idea.setIdeaID(ideaRepository.getMaxIdeaID() + 1);
        Idea testIdea = ideaRepository.save(idea);
        if (testIdea != null) {
            if (memberService.addMembers(ideaDTO.calculateTeamMemberNamesList(), idea.getIdeaID())) {
                hackathonService.incrementNumberOfIdeas(hackathonID);
                // Positive
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setStatus("SUCCESS");

                return responseDTO;
            }
        }
        throw new NullPointerException("Missing Required Field");
    }

    @Override
    public ResponseDTO updateIdea(IdeaDTO ideaDTO) throws BlankFieldException {
        Idea idea = IdeaDTOToIdea(ideaDTO);
        idea.setHackathonID(ideaRepository.getOne(ideaDTO.getIdeaID()).getHackathonID());
        idea = ideaRepository.save(idea);
        memberService.removeMembers(idea.getIdeaID());

        if (idea.getIdeaID() == ideaDTO.getIdeaID()) {
            if (memberService.addMembers(ideaDTO.calculateTeamMemberNamesList(), idea.getIdeaID())) {
                //Success
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setStatus("SUCCESS");
                return responseDTO;
            }
            throw new BlankFieldException("Edit Failed");

        }
        //Fail
        throw new BlankFieldException("Edit Failed");
    }

    IdeaDTO IdeaToIdeaDTO(Idea idea) {
        IdeaDTO ideaDTO = new IdeaDTO();
        ideaDTO.setIdeaID(idea.getIdeaID());
        ideaDTO.setCategoryName(idea.getIdeaCategory().getCategoryName());
        ideaDTO.setIdeaDetails(idea.getIdeaDetails());
        ideaDTO.setIdeaSummary(idea.getIdeaSummary());
        ideaDTO.setTeamMemberNames(memberService.getMembersPerIdea(idea.getIdeaID()));
        ideaDTO.setTotalLikes(idea.getLikeCount());

        return ideaDTO;
    }

    @Override
    public ResponseDTO likeIdea(LikeDTO likeDTO) throws BlankFieldException {

        Likes likes = new Likes();
        likes.setMemberID(memberService.getMemberByID(likeDTO.getMemberID()));
        likes.setIdeaID(ideaRepository.getOne(likeDTO.getideaID()));
        int likeID = likeService.addLike(likes);
        if (likeID > 0) {
            int flag = ideaRepository.addLikeToIdea(likeDTO.getideaID());
            //add to likes table
            if (flag > 0) {
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setStatus("SUCCESS");
                return responseDTO;
            }
        }
        throw new BlankFieldException("Failed to Like ");
    }

    @Override
    public ResponseDTO dislikeIdea(LikeDTO likeDTO) throws BlankFieldException {
        Likes likes = new Likes();
        likes.setLikeID(likeDTO.getLikeID());

        int likeID = likeService.removeLike(likes);
        if (likeID > 0) {
            int flag = ideaRepository.removeLikeFromIdea(likeDTO.getideaID());
            //add to likes table
            if (flag > 0) {
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setStatus("SUCCESS");
                return responseDTO;
            }
        }
        throw new BlankFieldException("Failed to Dislike");
    }

    Idea IdeaDTOToIdea(IdeaDTO ideaDTO) {
        Idea idea = new Idea();

        idea.setIdeaID(ideaDTO.getIdeaID());
        idea.setIdeaCategory(categoryService.getCategoryByName(ideaDTO.getCategoryName()));
        idea.setIdeaDetails(ideaDTO.getIdeaDetails());
        idea.setIdeaSummary(ideaDTO.getIdeaSummary());
        idea.setLikeCount(ideaDTO.getTotalLikes());
        idea.setTeamCount(ideaDTO.getTeamCount());

        //idea.setHackathonID(hackathonService.getHackathonByID(ideaDTO.get));

        return idea;
    }
}