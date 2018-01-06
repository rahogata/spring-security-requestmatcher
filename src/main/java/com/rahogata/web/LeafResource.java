package com.rahogata.web;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rahogata.beans.Leaf;

@RestController
@RequestMapping("/leaf")
public class LeafResource {

    private List<Leaf> leaves = new LinkedList<>();
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Leaf> addLeaf(@RequestBody @Valid Leaf leaf) {
        leaves.add(leaf);
        leaf.setId(leaves.size());
        return ResponseEntity.ok(leaf);
    }
    
    @RequestMapping("/{id}")
    public ResponseEntity<Leaf> getLeaf(@PathVariable int id) {
        int index = --id;
        if(leaves.isEmpty() || index < 0 || index >= leaves.size()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(leaves.get(index));
    }
}
