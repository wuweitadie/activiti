package com.lxd.activiti;

//import com.lxd.activiti.service.UserService;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
class ActivitiApplicationTests {

    /**
     * 流程定义相关操作
     */
    @Autowired
    private ProcessRuntime processRuntime;

    /**
     * 任务相关操作
     */
    @Autowired
    private TaskRuntime taskRuntime;

//    @Autowired
//    private UserService userService;

    @Autowired
    private RepositoryService repositoryService;

    @Test
    void contextLoads() {
    }

    /**
     * 查看流程定义
     */
    @Test
    public void testQueryProcessDefinition() {

//        userService.logInAs("salaboy");
        //分页查询流程定义信息
        Page<ProcessDefinition> page = processRuntime.processDefinitions(Pageable.of(0, 10));
        int totalItems = page.getTotalItems();
        System.out.println("查看部署流程的个数 = " + totalItems);

        List<ProcessDefinition> content = page.getContent();
        for (ProcessDefinition processDefinition : content) {
            String id = processDefinition.getId();
            System.out.println("当前部署的流程定义的id = " + id);
        }
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcessInstance() {
//        userService.logInAs("salaboy");
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("test").build());
        System.out.println("流程实例的id = " + processInstance.getId());
    }

    /**
     * 任务分页查询
     */
    @Test
    public void testQueryTask() {
//        userService.logInAs("salaboy");

        Page<Task> page = taskRuntime.tasks(Pageable.of(0, 10));

        int totalItems = page.getTotalItems();
        System.out.println("任务的总数 = " + totalItems);
        for (Task task : page.getContent()) {
            String id = task.getId();
            System.out.println("任务的id = " + id);
            String name = task.getName();
            System.out.println("任务名称 = " + name);
        }
    }

    /**
     * 测试查询任务并完成任务
     */
    @Test
    public void testQueryTaskAndCompleteTask(){
//        userService.logInAs("salaboy");

        Page<Task> page = taskRuntime.tasks(Pageable.of(0, 10));

        int totalItems = page.getTotalItems();
        System.out.println("任务的总数 = " + totalItems);
        for (Task task : page.getContent()) {
            String id = task.getId();
            System.out.println("任务的id = " + id);
            String name = task.getName();
            System.out.println("任务名称 = " + name);

            //拾取任务
            taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(id).build());

            //完成任务
            taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(id).build());

        }
    }

}
