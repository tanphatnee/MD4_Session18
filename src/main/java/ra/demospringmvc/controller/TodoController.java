package ra.demospringmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.demospringmvc.model.Todo;
import ra.demospringmvc.service.TodoService;

import java.util.List;

@Controller
@RequestMapping("/TodoController")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping
    public String  list(Model model){
        // lấy dữ liệu
        List<Todo> todoList  = todoService.findAll();
        model.addAttribute("list",todoList);
        return "todo/list";
    }


    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") int id){
        // lấy ra theo dường dẫn
        Todo todoEdit = todoService.findById(id);
//        model.addAttribute("todo",todoEdit);
//        return "todo/edit";

        ModelAndView modelAndView = new ModelAndView("todo/edit","todo",todoEdit);
        return modelAndView;
    }
    @PostMapping ("edit")
    public  String update(@ModelAttribute("todo") Todo todo){
        todoService.save(todo);
        return "redirect:/TodoController";
    }
    @GetMapping("delete/{id}")
    public  String delete(@PathVariable("id") int id){
        todoService.delete(id);
        return "redirect:/TodoController";
    }
}
