package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(DivisionController)
@Mock(Division)
class DivisionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/division/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.divisionInstanceList.size() == 0
        assert model.divisionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.divisionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.divisionInstance != null
        assert view == '/division/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/division/show/1'
        assert controller.flash.message != null
        assert Division.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/division/list'

        populateValidParams(params)
        def division = new Division(params)

        assert division.save() != null

        params.id = division.id

        def model = controller.show()

        assert model.divisionInstance == division
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/division/list'

        populateValidParams(params)
        def division = new Division(params)

        assert division.save() != null

        params.id = division.id

        def model = controller.edit()

        assert model.divisionInstance == division
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/division/list'

        response.reset()

        populateValidParams(params)
        def division = new Division(params)

        assert division.save() != null

        // test invalid parameters in update
        params.id = division.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/division/edit"
        assert model.divisionInstance != null

        division.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/division/show/$division.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        division.clearErrors()

        populateValidParams(params)
        params.id = division.id
        params.version = -1
        controller.update()

        assert view == "/division/edit"
        assert model.divisionInstance != null
        assert model.divisionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/division/list'

        response.reset()

        populateValidParams(params)
        def division = new Division(params)

        assert division.save() != null
        assert Division.count() == 1

        params.id = division.id

        controller.delete()

        assert Division.count() == 0
        assert Division.get(division.id) == null
        assert response.redirectedUrl == '/division/list'
    }
}
