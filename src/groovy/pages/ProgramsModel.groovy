package pages

import geb.Module
import geb.Page

/**
 * Represents the Programs Model page.
 */
class ProgramsModel extends Page {

    static url = 'admin/programsModel'
    static at = {
        title == "Programs model | Admin"
    }
    static content = {
        programs { moduleList Program, $('ul.programs li.item') }
        addProgramButton { $('[data-bind="click:addProgram"]')}
        saveButton { $('[data-bind*="click:save"]') }
    }

    def addProgram() {
        addProgramButton.click()
    }

    def save() {
        saveButton.click()
    }

}


class Program extends Module {

    static content = {
        nameSpan { $('[data-bind*="clickToEdit:name"') }
        nameInput { nameSpan.find('input') }
        deleteIcon { $('i.icon-remove')}
        name { nameSpan.find('a').text() }
        reportsViaMERIT { $('#isMeritProgramme') }
        reportingPeriod { $('#reportingPeriod') }
        reportingPeriodAlignedToCalendar { $('#reportingPeriodAlignedToCalendar') }
        projectsStartOnContractDates { $('#projectDatesContracted') }
        activities { $('.program-activities').find('input[name=activity]') }

        subprograms { isSelected()? moduleList( SubProgram, $('ul.subprograms li.item')) : [] }
    }

    def isSelected() {
        $('[data-bind*="visible:isSelected"]').isDisabled()
    }

    def select() {
        nameSpan.click()
    }
    def edit() {
        interact {doubleClick(nameSpan)}
    }
    def delete() {
        deleteIcon.click()
    }
}


class SubProgram extends Module {
    static content = {
        nameSpan { $('[data-bind*="clickToEdit:name"') }
        name { nameSpan.find('a').text() }
        startDate { $('#startDate') }
        endDate { $('#endDate') }
        themes { moduleList Theme, $('ul.themes li') }
    }
}

class Theme extends Module {
    static content = {
        nameSpan { $('[data-bind*="clickToEdit:name"') }
        name { nameSpan.find('a').text() }
    }
}


