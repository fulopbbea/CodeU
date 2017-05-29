/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author bea
 */
class KeyNotFoundException extends NullPointerException {
    
    public KeyNotFoundException(int key) {
        String message = "Key " + key + " not found in the tree.";
        System.err.println(message);
    }
}
