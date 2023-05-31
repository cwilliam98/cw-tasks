const people = [
    {
        "title": "Complete project proposal",
        "description": "Finish the proposal for the upcoming project.",
        "status": "pending",
        "priority": "high",
        "deadline": "2023-06-01",
        "assignedUser": "john@example.com"
    },
    {
        "title": "Implement user authentication",
        "description": "Develop the user authentication feature for the application.",
        "status": "in-progress",
        "priority": "medium",
        "deadline": "2023-06-10",
        "assignedUser": "emma@example.com"
    },
    {
        "title": "Fix bug in task sorting",
        "description": "Investigate and fix the bug causing incorrect sorting of tasks.",
        "status": "pending",
        "priority": "low",
        "deadline": "2023-06-15",
        "assignedUser": "john@example.com"
    },
    {
        "title": "Fix bug in task sorting",
        "description": "Investigate and fix the bug causing incorrect sorting of tasks.",
        "status": "pending",
        "priority": "low",
        "deadline": "2023-06-15",
        "assignedUser": "john@example.com"
    }
]


export default function TaskList() {
    return (
        <>
            <div className="grid grid-cols-3 gap-4">
                <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 border-2 border-stone-500 rounded-lg px-2">
                    Pending
                    {people.map((person) => (
                    <div key={person.title} className="flex justify-between gap-x-1 py-5 text-sm bg-slate-100 rounded-lg">
                        <div className="flex gap-x-4">
                            <div className="min-w-0 flex-auto pl-1">
                                <p>{person.description}</p>
                                <p className="">{person.priority}</p>
                            </div>
                        </div>
                        <div className="hidden sm:flex sm:flex-col sm:items-end pr-1">
                            <p className="">{person.deadline}</p>
                            <p className="">{person.assignedUser}</p>

                        </div>
                    </div>
                ))}
                </div>
                <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 border-2 border-stone-500 rounded-lg px-2">
                    In Progress
                    {people.map((person) => (
                    <div key={person.title} className="flex justify-between gap-x-1 py-5 text-sm  bg-slate-100 rounded-lg">
                        <div className="flex gap-x-4">
                            <div className="min-w-0 flex-auto pl-1">
                                <p>{person.description}</p>
                                <p className="">{person.priority}</p>
                            </div>
                        </div>
                        <div className="hidden sm:flex sm:flex-col sm:items-end pr-1">
                            <p className="">{person.deadline}</p>
                            <p className="">{person.assignedUser}</p>

                        </div>
                    </div>
                ))}
                    </div>
                <div className="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 border-2 border-stone-500 rounded-lg px-2">
                    Completed
                    {people.map((person) => (
                    <div key={person.title} className="flex justify-between gap-x-1 py-5 text-sm  bg-slate-100 rounded-lg">
                        <div className="flex gap-x-4">
                            <div className="min-w-0 flex-auto pl-1">
                                <p>{person.description}</p>
                                <p className="">{person.priority}</p>
                            </div>
                        </div>
                        <div className="hidden sm:flex sm:flex-col sm:items-end pr-1">
                            <p className="">{person.deadline}</p>
                            <p className="">{person.assignedUser}</p>

                        </div>
                    </div>
                ))}
                    </div>
            </div>
        </>
    )
}
