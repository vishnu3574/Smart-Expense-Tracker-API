package com.vishnu.smartexpensetracker.controller;

import com.vishnu.smartexpensetracker.model.Expense;
import com.vishnu.smartexpensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepo;

    @GetMapping("/")
    public String home(@RequestParam(required = false) String category, Model model) {
        
        List<Expense> expensesToShow;
        
        if (category != null && !category.isEmpty()) {
            expensesToShow = expenseRepo.findByCategory(category);
        } else {
            expensesToShow = expenseRepo.findAll();
        }
        
        BigDecimal total = expensesToShow.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        model.addAttribute("expenses", expensesToShow);
        model.addAttribute("total", total);
        model.addAttribute("selectedCategory", category);
        
        return "index";
    }

    @PostMapping("/add")
    public String addExpenseFromForm(@RequestParam String title, 
                                     @RequestParam BigDecimal amount, 
                                     @RequestParam String category, 
                                     @RequestParam String date) {
        Expense expense = new Expense();
        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(LocalDate.parse(date));
        expenseRepo.save(expense);
        
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpenseFromWeb(@PathVariable Long id) {
        expenseRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/api/expenses")
    @ResponseBody
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepo.save(expense);
    }
    
    @GetMapping("/api/expenses")
    @ResponseBody
    public List<Expense> getAllExpenses(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return expenseRepo.findByCategory(category);
        }
        return expenseRepo.findAll();
    }
    
    @GetMapping("/api/expenses/total")
    @ResponseBody
    public BigDecimal getTotal(@RequestParam(required = false) String category) {
        List<Expense> expenses;
        if (category != null && !category.isEmpty()) {
            expenses = expenseRepo.findByCategory(category);
        } else {
            expenses = expenseRepo.findAll();
        }
        return expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @DeleteMapping("/api/expenses/{id}")
    @ResponseBody
    public String deleteExpense(@PathVariable Long id) {
        expenseRepo.deleteById(id);
        return "Expense deleted with id: " + id;
    }
}