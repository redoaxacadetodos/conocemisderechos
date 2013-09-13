package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(PNDesarrolloController)
@Mock(PNDesarrollo)
class PNDesarrolloControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/PNDesarrollo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.PNDesarrolloInstanceList.size() == 0
        assert model.PNDesarrolloInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.PNDesarrolloInstance != null
    }

    void testSave() {
        controller.save()

        assert model.PNDesarrolloInstance != null
        assert view == '/PNDesarrollo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/PNDesarrollo/show/1'
        assert controller.flash.message != null
        assert PNDesarrollo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/PNDesarrollo/list'

        populateValidParams(params)
        def PNDesarrollo = new PNDesarrollo(params)

        assert PNDesarrollo.save() != null

        params.id = PNDesarrollo.id

        def model = controller.show()

        assert model.PNDesarrolloInstance == PNDesarrollo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/PNDesarrollo/list'

        populateValidParams(params)
        def PNDesarrollo = new PNDesarrollo(params)

        assert PNDesarrollo.save() != null

        params.id = PNDesarrollo.id

        def model = controller.edit()

        assert model.PNDesarrolloInstance == PNDesarrollo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/PNDesarrollo/list'

        response.reset()

        populateValidParams(params)
        def PNDesarrollo = new PNDesarrollo(params)

        assert PNDesarrollo.save() != null

        // test invalid parameters in update
        params.id = PNDesarrollo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/PNDesarrollo/edit"
        assert model.PNDesarrolloInstance != null

        PNDesarrollo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/PNDesarrollo/show/$PNDesarrollo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        PNDesarrollo.clearErrors()

        populateValidParams(params)
        params.id = PNDesarrollo.id
        params.version = -1
        controller.update()

        assert view == "/PNDesarrollo/edit"
        assert model.PNDesarrolloInstance != null
        assert model.PNDesarrolloInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/PNDesarrollo/list'

        response.reset()

        populateValidParams(params)
        def PNDesarrollo = new PNDesarrollo(params)

        assert PNDesarrollo.save() != null
        assert PNDesarrollo.count() == 1

        params.id = PNDesarrollo.id

        controller.delete()

        assert PNDesarrollo.count() == 0
        assert PNDesarrollo.get(PNDesarrollo.id) == null
        assert response.redirectedUrl == '/PNDesarrollo/list'
    }
}
