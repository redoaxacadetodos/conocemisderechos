package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(VariableController)
@Mock(Variable)
class VariableControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/variable/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.variableInstanceList.size() == 0
        assert model.variableInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.variableInstance != null
    }

    void testSave() {
        controller.save()

        assert model.variableInstance != null
        assert view == '/variable/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/variable/show/1'
        assert controller.flash.message != null
        assert Variable.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/variable/list'

        populateValidParams(params)
        def variable = new Variable(params)

        assert variable.save() != null

        params.id = variable.id

        def model = controller.show()

        assert model.variableInstance == variable
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/variable/list'

        populateValidParams(params)
        def variable = new Variable(params)

        assert variable.save() != null

        params.id = variable.id

        def model = controller.edit()

        assert model.variableInstance == variable
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/variable/list'

        response.reset()

        populateValidParams(params)
        def variable = new Variable(params)

        assert variable.save() != null

        // test invalid parameters in update
        params.id = variable.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/variable/edit"
        assert model.variableInstance != null

        variable.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/variable/show/$variable.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        variable.clearErrors()

        populateValidParams(params)
        params.id = variable.id
        params.version = -1
        controller.update()

        assert view == "/variable/edit"
        assert model.variableInstance != null
        assert model.variableInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/variable/list'

        response.reset()

        populateValidParams(params)
        def variable = new Variable(params)

        assert variable.save() != null
        assert Variable.count() == 1

        params.id = variable.id

        controller.delete()

        assert Variable.count() == 0
        assert Variable.get(variable.id) == null
        assert response.redirectedUrl == '/variable/list'
    }
}
