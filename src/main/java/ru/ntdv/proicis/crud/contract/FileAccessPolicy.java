package ru.ntdv.proicis.crud.contract;

public enum FileAccessPolicy {
    Public,

    Owner,
    Administrators,
    Moderators,
    Mentors,
    Participants,

    Team;

    private static final FileAccessPolicy[] Registered = { Owner, Administrators, Moderators, Mentors, Participants };

}
