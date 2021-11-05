package manageme.model.util;

import manageme.model.ManageMe;
import manageme.model.ReadOnlyManageMe;
import manageme.model.link.Link;
import manageme.model.link.LinkAddress;
import manageme.model.link.LinkModule;
import manageme.model.link.LinkName;
import manageme.model.module.Module;
import manageme.model.module.ModuleName;
import manageme.model.task.*;

/**
 * Contains utility methods for populating {@code ManageMe} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleName("CS2100")),
            new Module(new ModuleName("CS2101")),
            new Module(new ModuleName("CS2103T")),
            new Module(new ModuleName("CS2105")),
            new Module(new ModuleName("CS2106"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("CS2100 Assignment 3"), new TaskDescription("MSI, Sequential Circuit, Pipelining"),
                    new TaskModule("CS2100"), TaskTime.empty(), new TaskTime("2021-11-08T13:00")),
            new Task(new TaskName("CS2105 Assignment 3"), new TaskDescription("DASH, WebRTC"),
                    new TaskModule("CS2105"), TaskTime.empty(), new TaskTime("2021-11-07T23:59")),
            new Task(new TaskName("CS2100 Finals"), new TaskDescription("All topics, close book with cheatsheet"),
                    new TaskModule("CS2100"), new TaskTime("2021-11-20T09:00"), new TaskTime("2021-11-20T11:00")),
            new Task(new TaskName("CS2105 Finals"), new TaskDescription("All topics, open book without internet"),
                    new TaskModule("CS2105"), new TaskTime("2021-11-29T09:00"), new TaskTime("2021-11-29T11:00")),
            new Task(new TaskName("CS2103T Finals"), new TaskDescription("All topics, test UML drawing"),
                    new TaskModule("CS2103T"), new TaskTime("2021-11-23T17:00"), new TaskTime("2021-11-23T19:00")),
            new Task(new TaskName("CS2103T PE"), new TaskDescription("Bug catching"),
                    new TaskModule("CS2103T"), new TaskTime("2021-11-12T16:00"), new TaskTime("2021-11-12T18:00")),
            new Task(new TaskName("tP deadline"), new TaskDescription("Submit deliverables"),
                    new TaskModule("CS2103T"), TaskTime.empty(), new TaskTime("2021-11-08T23:59")),
            new Task(new TaskName("CS2101 Reflection"), new TaskDescription("15% of overall grade"),
                    new TaskModule("CS2101"), TaskTime.empty(), new TaskTime("2021-11-11T12:00")),
            new Task(new TaskName("Reading week"), new TaskDescription("Revision time"),
                    new TaskModule(""), new TaskTime("2021-11-15T00:00"), new TaskTime("2021-11-19T23:59")),
            new Task(new TaskName("Cut hair"), new TaskDescription("Getting kinda long"),
                    new TaskModule(""), TaskTime.empty(), TaskTime.empty()),
            new Task(new TaskName("Dinner with friends"), new TaskDescription("At Utown"),
                    new TaskModule(""), TaskTime.empty(), TaskTime.empty())
        };
    }

    public static Link[] getSampleLinks() {
        return new Link[] {
            new Link(new LinkName("tP Github"),
                    new LinkAddress("https://github.com/AY2122S1-CS2103T-W11-3/tp"), new LinkModule("CS2103T")),
            new Link(new LinkName("tP UG"),
                    new LinkAddress("https://ay2122s1-cs2103t-w11-3.github.io/tp/UserGuide.html"),
                    new LinkModule("CS2103T")),
            new Link(new LinkName("tP DG"),
                    new LinkAddress("https://ay2122s1-cs2103t-w11-3.github.io/tp/DeveloperGuide.html"),
                    new LinkModule("CS2103T")),
            new Link(new LinkName("CS2100 Lecture"),
                    new LinkAddress("https://nus-sg.zoom.us/j/84884962542?pwd=NEF6SVdQUUgwWmRwUmgvSy9WTlNDQT09"),
                    new LinkModule("CS2100")),
            new Link(new LinkName("Google"),
                    new LinkAddress("https://www.google.com/"), new LinkModule("")),
            new Link(new LinkName("Replace with your own local file"),
                    new LinkAddress("file://C:/Users/Public"), new LinkModule(""))
        };
    }

    public static ReadOnlyManageMe getSampleManageMe() {
        ManageMe sampleMm = new ManageMe();

        for (Module sampleModule : getSampleModules()) {
            sampleMm.addModule(sampleModule);
        }

        for (Task sampleTask : getSampleTasks()) {
            sampleMm.addTask(sampleTask);
        }

        for (Link sampleLink : getSampleLinks()) {
            sampleMm.addLink(sampleLink);
        }

        return sampleMm;
    }

}
