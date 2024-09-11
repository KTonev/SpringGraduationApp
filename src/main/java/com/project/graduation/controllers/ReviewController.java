package com.project.graduation.controllers;

import com.project.graduation.data.dto.ReviewDto;
import com.project.graduation.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ReviewController {
    private ReviewService reviewService;

    @GetMapping("/thesis/{thesisId}")
    public String getReviewsByThesis(@PathVariable("thesisId") long thesisId, Model model) {
        List<ReviewDto> reviews = reviewService.getReviewsByThesisId(thesisId);
        model.addAttribute("reviews", reviews);
        return "reviewTemplates/reviewList";
    }

    @GetMapping("/create")
    public String createReviewForm(@RequestParam("thesisId") long thesisId,
                                   @RequestParam("teacherId") long teacherId,
                                   Model model) {
        model.addAttribute("thesisId", thesisId);
        model.addAttribute("teacherId", teacherId);
        return "reviewTemplates/createReview";
    }

    @PostMapping("/create")
    public String createReview(@RequestParam("thesisId") long thesisId,
                               @RequestParam("teacherId") long teacherId,
                               @ModelAttribute("review") ReviewDto reviewDto) {

        reviewService.createReview(reviewDto, thesisId, teacherId);
        return "redirect:/menuTeacher?reviewSuccess";
    }

}
