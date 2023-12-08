//package com.rkt.app.controller;
//
//import com.rkt.app.dto.requestDto.MenuDto;
//import com.rkt.app.service.MenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/menu")
//public class MenuController {
//    @Autowired
//    private MenuService menuService;
//
//    @PostMapping("/add")
//    public ResponseEntity<?> addMenu(@RequestBody()MenuDto menuDto) {
//        menuService.addMenu(menuDto);
//        return ResponseEntity.ok("Menu added successfully...");
//    }
//
//    @GetMapping("/get-all")
//    public ResponseEntity<?> getAllMenu() {
//        return ResponseEntity.ok(menuService.getAllMenu());
//    }
//
//    @GetMapping("/get-all-parent-menu")
//    public ResponseEntity<?> getAllParentMenuId(){
//        return ResponseEntity.ok(menuService.getAllParentMenuId());
//    }
//
//    @DeleteMapping("/delete-parent/{parentId}")
//    public ResponseEntity<?> deleteParentMenu(@PathVariable("parentId") long parentId) {
//
//        menuService.deleteParentMenu(parentId);
//
//        return ResponseEntity.ok("menu deleted successfully..");
//    }
//
//    @DeleteMapping("/delete-child/{childId}")
//    public ResponseEntity<?> deleteChildMenu(@PathVariable("childId") long childId) {
//        menuService.deleteChildMenu(childId);
//
//        return ResponseEntity.ok("Child menu deleted successfully.");
//    }
//}
