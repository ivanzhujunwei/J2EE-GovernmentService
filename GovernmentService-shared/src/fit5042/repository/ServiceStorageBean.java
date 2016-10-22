/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.repository;

import fit5042.repository.entities.Service;
import java.util.List;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
public interface ServiceStorageBean
{
    public List<Service> getServiceTemplates(); 
}
