package mx.gob.redoaxaca.cednna.domino



import org.junit.*
import grails.test.mixin.*

@TestFor(MunicipioController)
@Mock(Municipio)
class MunicipioControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/municipio/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.municipioInstanceList.size() == 0
        assert model.municipioInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.municipioInstance != null
    }

    void testSave() {
        controller.save()

        assert model.municipioInstance != null
        assert view == '/municipio/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/municipio/show/1'
        assert controller.flash.message != null
        assert Municipio.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/municipio/list'

        populateValidParams(params)
        def municipio = new Municipio(params)

        assert municipio.save() != null

        params.id = municipio.id

        def model = controller.show()

        assert model.municipioInstance == municipio
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/municipio/list'

        populateValidParams(params)
        def municipio = new Municipio(params)

        assert municipio.save() != null

        params.id = municipio.id

        def model = controller.edit()

        assert model.municipioInstance == municipio
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/municipio/list'

        response.reset()

        populateValidParams(params)
        def municipio = new Municipio(params)

        assert municipio.save() != null

        // test invalid parameters in update
        params.id = municipio.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/municipio/edit"
        assert model.municipioInstance != null

        municipio.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/municipio/show/$municipio.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        municipio.clearErrors()

        populateValidParams(params)
        params.id = municipio.id
        params.version = -1
        controller.update()

        assert view == "/municipio/edit"
        assert model.municipioInstance != null
        assert model.municipioInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/municipio/list'

        response.reset()

        populateValidParams(params)
        def municipio = new Municipio(params)

        assert municipio.save() != null
        assert Municipio.count() == 1

        params.id = municipio.id

        controller.delete()

        assert Municipio.count() == 0
        assert Municipio.get(municipio.id) == null
        assert response.redirectedUrl == '/municipio/list'
    }
}
